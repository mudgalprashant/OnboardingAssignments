package com.newsletter.user.services;

import com.newsletter.user.constants.Constant;
import com.newsletter.user.enums.Role;
import com.newsletter.user.models.User;
import com.newsletter.user.repo.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The type User details service.
 */
@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  private final UserRepo userRepo;


  private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

  /**
   * Create user user.
   *
   * @param user the user
   * @return the user
   */
  @CachePut(value = Constant.CACHE_VALUE)
  public User createUser(User user){
    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    return userRepo.save(user);
  }


  /**
   * Gets all user.
   *
   * @return the all user
   */
  @Cacheable(value = Constant.CACHE_VALUE)
  public List<User> getAllUser() {
    return (List<User>)userRepo.findAll();
  }

  /**
   * Gets user by id.
   *
   * @param id the id
   * @return the user by id
   */
  @Cacheable(value = Constant.CACHE_VALUE, key = "#id")
  public User getUserById(Long id) {
    return userRepo.findById(id).orElseThrow();
  }

  /**
   * Gets user by email.
   *
   * @param email the email
   * @return the user by email
   */
  @Cacheable(value = Constant.CACHE_VALUE, key = "#email")
  public User getUserByEmail(String email) {
    return userRepo.findByEmail(email).orElseThrow();
  }

  /**
   * Update user user.
   *
   * @param newUser the new user
   * @param id      the id
   * @return the user
   */
  @Cacheable(value = Constant.CACHE_VALUE, key = "#id")
  public User updateUser(User newUser, Long id) {
    User user = getUserById(id);
    copyNonNull(newUser, user);
    user.setId(id);
    user.setPassword(
        bCryptPasswordEncoder
            .encode(user.getPassword())
    );
    return userRepo.save(user);
  }

  /**
   * Delete user.
   *
   * @param id the id
   */
  @CacheEvict(value = Constant.CACHE_VALUE)
  public void deleteUser(Long id) {
    userRepo.deleteById(id);
  }

  /**
   * Copy non null.
   *
   * @param source      the source
   * @param destination the destination
   */
  public void copyNonNull(Object source, Object destination){
    BeanUtils.copyProperties(source, destination,
        getNullPropertyNames(source));
  }

  /**
   * Returns an array of null properties of an object.
   */
  private String[] getNullPropertyNames (Object source) {
    final BeanWrapper src = new BeanWrapperImpl(source);
    java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();
    Set<String> emptyNames = new HashSet<>();
    for(java.beans.PropertyDescriptor pd : pds) {
      //check if value of this property is null then add it to the collection
      Object srcValue = src.getPropertyValue(pd.getName());
      if (srcValue == null) emptyNames.add(pd.getName());
    }
    String[] result = new String[emptyNames.size()];
    return emptyNames.toArray(result);
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    return mapUserToUserDetails(
        userRepo
            .findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException(Constant.USER_NOT_FOUND_MESSAGE))
    );
  }

  private UserDetails mapUserToUserDetails(User user) {
    return new org.springframework.security.core.userdetails.User(
        user.getEmail(),
        user.getPassword(),
        List.of(user.getRole())
    );
  }

}
