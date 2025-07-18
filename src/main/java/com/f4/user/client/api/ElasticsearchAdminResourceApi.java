package com.f4.user.client.api;

import com.f4.user.client.ApiClient;
import com.f4.user.client.EncodingUtils;
import com.f4.user.client.model.ApiResponse;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import feign.*;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2025-06-28T09:20:35.299334524+07:00[Asia/Ho_Chi_Minh]", comments = "Generator version: 7.13.0")
public interface ElasticsearchAdminResourceApi extends ApiClient.Api {


  /**
   * 
   * 
   */
  @RequestLine("POST /api/admin/elasticsearch/reindex/reels")
  @Headers({
    "Accept: application/json",
  })
  void reindexReels();

  /**
   * 
   * Similar to <code>reindexReels</code> but it also returns the http response headers .
   * 
   */
  @RequestLine("POST /api/admin/elasticsearch/reindex/reels")
  @Headers({
    "Accept: application/json",
  })
  ApiResponse<Void> reindexReelsWithHttpInfo();


}
