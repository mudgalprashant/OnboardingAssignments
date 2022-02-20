package com.newsletter.user.services;

import com.newsletter.user.models.User;
import com.newsletter.user.repo.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * The type User service.
 */
@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService, UserDetailsService {

  private final UserRepo userRepo;


  private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();


  @Override
  public User createUser(User user){
    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    return userRepo.save(user);
  }

  @Override
  public List<User> getAllUser() {
    return (List<User>)userRepo.findAll();
  }

  @Override
  public User getUserById(long id) {
    return userRepo.findById(id).orElseThrow();
  }

  @Override
  public User getUserByEmail(String email) {
    return userRepo.findByEmail(email).orElseThrow();
  }

  @Override
  public User updateUser(User newUser, long id) {
    User user = userRepo.findById(id).orElseThrow();
    copyNonNull(newUser, user);
    user.setId(id);
    user.setPassword(
        bCryptPasswordEncoder
            .encode(user.getPassword())
    );
    return userRepo.save(user);
  }

  @Override
  public void deleteUser(long id) {
    userRepo.deleteById(id);
  }

  @Override
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
            .orElseThrow(() -> new UsernameNotFoundException("User not found"))
            );
  }

  private UserDetails mapUserToUserDetails(User user) {
    return new org.springframework.security.core.userdetails.User(
        user.getEmail(),
        user.getPassword(),
        Collections.emptyList()
    );
  }

}
