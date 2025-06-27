package com.f4.user.client.api;

import com.f4.user.client.ApiClient;
import com.f4.user.client.EncodingUtils;
import com.f4.user.client.model.ApiResponse;

import com.f4.user.client.model.ReelRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import feign.*;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2025-06-27T09:24:56.232976487+07:00[Asia/Ho_Chi_Minh]", comments = "Generator version: 7.13.0")
public interface MsCommentlikeKafkaResourceApi extends ApiClient.Api {


  /**
   * 
   * 
   * @param reelRequest  (required)
   * @return String
   */
  @RequestLine("POST /api/kafka-test/send-reel-direct")
  @Headers({
    "Content-Type: application/json",
    "Accept: */*",
  })
  String sendReelDirectly(@javax.annotation.Nonnull ReelRequest reelRequest);

  /**
   * 
   * Similar to <code>sendReelDirectly</code> but it also returns the http response headers .
   * 
   * @param reelRequest  (required)
   * @return A ApiResponse that wraps the response boyd and the http headers.
   */
  @RequestLine("POST /api/kafka-test/send-reel-direct")
  @Headers({
    "Content-Type: application/json",
    "Accept: */*",
  })
  ApiResponse<String> sendReelDirectlyWithHttpInfo(@javax.annotation.Nonnull ReelRequest reelRequest);


}
