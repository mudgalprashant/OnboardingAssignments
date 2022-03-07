package com.newsletter.user.constants;

/**
 * The type Constant.
 */
public class Constant {
  /**
   * The constant EMAIL_ALREADY_EXISTS.
   */
  public static final String EMAIL_ALREADY_EXISTS =
      "Entered email is already registered. Please try another user";
  /**
   * The constant ACCEPTED.
   */
  public static final String ACCEPTED = "Y";
  /**
   * The constant DENIED.
   */
  public static final String DENIED = "N";

  /**
   * The constant UNAUTHORIZED.
   */
  public static final String UNAUTHORIZED = "Unauthorized";

  /**
   * The constant SECURITY_HEADER.
   */
  public static final String SECURITY_HEADER = "Authorization";

  /**
   * The constant JWT_FETCH_FAILED.
   */
  public static final String JWT_FETCH_FAILED = "Unable to get JWT Token";

  /**
   * The constant JWT_EXPIRED.
   */
  public static final String JWT_EXPIRED = "JWT Token has expired";

  /**
   * The constant JWT_WITHOUT_BEARER.
   */
  public static final String JWT_WITHOUT_BEARER = "JWT Token does not begin with Bearer String";

  /**
   * The constant LOGIN_PAGE.
   */
  public static final String LOGIN_PAGE = "/login";

  /**
   * The constant USERNAME_PARAMETER.
   */
  public static final String USERNAME_PARAMETER = "email";

  /**
   * The constant CREATE_AUTH_TOKEN_MAPPING.
   */
  public static final String CREATE_AUTH_TOKEN_MAPPING = "/login";

  /**
   * The constant AUTHORIZE_SUBSCRIBER_MAPPING.
   */
  public static final String AUTHORIZE_SUBSCRIBER_MAPPING = "/authorize/subscriber/{id}";

  /**
   * The constant AUTHORIZE_PUBLISHER_MAPPING.
   */
  public static final String AUTHORIZE_PUBLISHER_MAPPING = "/authorize/publisher/{id}";

  /**
   * The constant AUTHORIZE_USER_MAPPING.
   */
  public static final String AUTHORIZE_USER_MAPPING = "/authorize/user/{id}";

  /**
   * The constant USER_DISABLED.
   */
  public static final String USER_DISABLED = "Provided user is disabled";

  /**
   * The constant INVALID_CREDENTIALS.
   */
  public static final String INVALID_CREDENTIALS = "Provided Credentials are invalid";

  /**
   * The constant CRUD_MAPPING.
   */
  public static final String CRUD_MAPPING = "/users";

  /**
   * The constant BY_ID_MAPPING.
   */
  public static final String BY_ID_MAPPING = "/{id}";

  /**
   * The constant BY_EMAIL_MAPPING.
   */
  public static final String BY_EMAIL_MAPPING = "/email/{email}";

  /**
   * The constant UNAUTHORIZED_ACCESS_MESSAGE.
   */
  public static final String UNAUTHORIZED_ACCESS_MESSAGE = "Permission Denied: Unauthorized access";

  /**
   * The constant USER_NOT_FOUND_MESSAGE.
   */
  public static final String USER_NOT_FOUND_MESSAGE = "User not found";

  /**
   * The constant CACHE_VALUE.
   */
  public static final String CACHE_VALUE = "users";

}
