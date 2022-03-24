package com.newsletter.subscribe.constants;

/**
 * The type Constant.
 */
public class Constant {

  /**
   * The constant SECURITY_HEADER.
   */
  public static final String SECURITY_HEADER = "Authorization";

  /**
   * The constant SUBSCRIBE_MAPPING.
   */
  public static final String SUBSCRIBE_MAPPING = "/subscribe";

  /**
   * The constant RENEW_MAPPING.
   */
  public static final String RENEW_MAPPING = "/renew";

  /**
   * The constant UNSUBSCRIBE_MAPPING.
   */
  public static final String UNSUBSCRIBE_MAPPING = "/unsubscribe";

  /**
   * The constant BY_ID_MAPPING.
   */
  public static final String BY_ID_MAPPING= "/{id}";

  /**
   * The constant CACHE_VALUE.
   */
  public static final String CACHE_VALUE = "subscribes";

  /**
   * The constant SUBSCRIPTION_SERVICE_FC_NAME.
   */
  public static final String SUBSCRIPTION_SERVICE_FC_NAME =
      "subscription-service";

  /**
   * The constant SUBSCRIPTION_SERVICE_FC_URL.
   */
  public static final String SUBSCRIPTION_SERVICE_FC_URL=
      "http://localhost:8081";

  /**
   * The constant USER_SERVICE_FC_NAME.
   */
  public static final String USER_SERVICE_FC_NAME =
      "user-service";

  /**
   * The constant USER_SERVICE_FC_URL.
   */
  public static final String USER_SERVICE_FC_URL =
      "http://localhost:8080";

  /**
   * The constant SUBSCRIPTION_SERVICE_GET_BY_ID_MAPPING.
   */
  public static final String SUBSCRIPTION_SERVICE_GET_BY_ID_MAPPING =
      "/newsletter/subscriptions/{id}";

  /**
   * The constant USER_SERVICE_GET_BY_ID_MAPPING.
   */
  public static final String USER_SERVICE_GET_BY_ID_MAPPING =
      "/newsletter/users/{id}";

  /**
   * The constant USER_SERVICE_AUTHORIZE_SUBSCRIBER_MAPPING.
   */
  public static final String USER_SERVICE_AUTHORIZE_SUBSCRIBER_MAPPING =
      "/newsletter/auth/authorize/subscriber";


  public static final String USER_SERVICE_BY_TOKEN_MAPPING = "newsletter/users/token/jwt";
  public static final String FIND_BY_SUBSCRIPTION_MAPPING = "/subscribe/subscription";
}
