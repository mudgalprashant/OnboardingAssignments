package com.newsletter.subscription.constants;

public class QueryConstant {

  public static final String SEARCH_BY_NAME_QUERY =
      "{\"multi_match\": {\"query\": \".*?0.*\", \"fields\": [\"name\"], \"fuzziness\" : \"AUTO\", \"prefix_length\" : 2}}";
  public static final String SEARCH_BY_CATEGORY_QUERY =
      "{\"multi_match\": {\"query\": \".*?0.*\", \"fields\": [\"category\"], \"fuzziness\" : \"0\", \"prefix_length\" : 0}}";

  public static final String SEARCH_BY_PUBLISHER_ID_QUERY =
      "{\"multi_match\": {\"query\": \"?0\", \"fields\": [\"publisherId\"], \"prefix_length\" : 0}}";

}
