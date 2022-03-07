package com.newsletter.email.constants;

/**
 * The type Constant.
 */
public class Constant {

  /**
   * The constant TOPIC.
   */
  public static final String TOPIC = "newsletter";

  /**
   * The constant GROUP_ID.
   */
  public static final String GROUP_ID = "pub-sub";

  /**
   * The constant SUCCESS_STATUS.
   */
  public static final String SUCCESS_STATUS = "Success";
  /**
   * The constant FAILURE_STATUS.
   */
  public static final String FAILURE_STATUS = "Failed";

  /**
   * The constant ACCEPTED.
   */
  public static final String ACCEPTED = "Y";
  /**
   * The constant DENIED.
   */
  public static final String DENIED = "N";

  /**
   * The constant SENDER_NOT_FOUND.
   */
  public static final String SENDER_NOT_FOUND = "Could not find the sender";

  /**
   * The constant UNAUTHORIZED_SENDER.
   */
  public static final String UNAUTHORIZED_SENDER = "Could not authorize and send email";

  /**
   * The constant SECURITY_HEADER.
   */
  public static final String SECURITY_HEADER = "Authorization";

  /**
   * The constant KAFKA_LISTENER_CONTAINER_FACTORY.
   */
  public static final String KAFKA_LISTENER_CONTAINER_FACTORY = "kafkaListenerContainerFactory";

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
   * The constant USER_SERVICE_GET_BY_EMAIL_MAPPING.
   */
  public static final String USER_SERVICE_GET_BY_EMAIL_MAPPING =
      "/newsletter/users/email/{email}";

  /**
   * The constant USER_SERVICE_AUTHORIZE_PUBLISHER_MAPPING.
   */
  public static final String USER_SERVICE_AUTHORIZE_PUBLISHER_MAPPING =
      "/newsletter/auth/authorize/publisher/{id}";


}
