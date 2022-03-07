package com.newsletter.subscribe.services;

import com.newsletter.subscribe.models.Sub;

public interface SubService {

  Sub subscribe(Sub sub);

  Sub renew(Long id);

  void unSubscribe(Long id);

}
