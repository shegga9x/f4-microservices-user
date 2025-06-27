package com.f4.user.client.api;

import com.f4.user.client.ApiClient;
import com.f4.user.client.EncodingUtils;
import com.f4.user.client.model.ApiResponse;

import com.f4.user.client.model.RedisUserDTO;
import java.util.UUID;
import com.f4.user.client.model.UserDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import feign.*;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2025-06-27T09:24:56.232976487+07:00[Asia/Ho_Chi_Minh]", comments = "Generator version: 7.13.0")
public interface UserResourceApi extends ApiClient.Api {


  /**
   * 
   * 
   * @param userDTO  (required)
   * @return UserDTO
   */
  @RequestLine("POST /api/users")
  @Headers({
    "Content-Type: application/json",
    "Accept: */*",
  })
  UserDTO createUser(@javax.annotation.Nonnull UserDTO userDTO);

  /**
   * 
   * Similar to <code>createUser</code> but it also returns the http response headers .
   * 
   * @param userDTO  (required)
   * @return A ApiResponse that wraps the response boyd and the http headers.
   */
  @RequestLine("POST /api/users")
  @Headers({
    "Content-Type: application/json",
    "Accept: */*",
  })
  ApiResponse<UserDTO> createUserWithHttpInfo(@javax.annotation.Nonnull UserDTO userDTO);



  /**
   * 
   * 
   * @param id  (required)
   */
  @RequestLine("DELETE /api/users/{id}")
  @Headers({
    "Accept: application/json",
  })
  void deleteUser(@Param("id") @javax.annotation.Nonnull UUID id);

  /**
   * 
   * Similar to <code>deleteUser</code> but it also returns the http response headers .
   * 
   * @param id  (required)
   */
  @RequestLine("DELETE /api/users/{id}")
  @Headers({
    "Accept: application/json",
  })
  ApiResponse<Void> deleteUserWithHttpInfo(@Param("id") @javax.annotation.Nonnull UUID id);



  /**
   * 
   * 
   * @return List&lt;UserDTO&gt;
   */
  @RequestLine("GET /api/users")
  @Headers({
    "Accept: */*",
  })
  List<UserDTO> getAllUsers();

  /**
   * 
   * Similar to <code>getAllUsers</code> but it also returns the http response headers .
   * 
   * @return A ApiResponse that wraps the response boyd and the http headers.
   */
  @RequestLine("GET /api/users")
  @Headers({
    "Accept: */*",
  })
  ApiResponse<List<UserDTO>> getAllUsersWithHttpInfo();



  /**
   * 
   * 
   * @param id  (required)
   * @return UserDTO
   */
  @RequestLine("GET /api/users/{id}")
  @Headers({
    "Accept: */*",
  })
  UserDTO getUser(@Param("id") @javax.annotation.Nonnull UUID id);

  /**
   * 
   * Similar to <code>getUser</code> but it also returns the http response headers .
   * 
   * @param id  (required)
   * @return A ApiResponse that wraps the response boyd and the http headers.
   */
  @RequestLine("GET /api/users/{id}")
  @Headers({
    "Accept: */*",
  })
  ApiResponse<UserDTO> getUserWithHttpInfo(@Param("id") @javax.annotation.Nonnull UUID id);



  /**
   * 
   * 
   * @param id  (required)
   * @return RedisUserDTO
   */
  @RequestLine("GET /api/users/{id}/redis")
  @Headers({
    "Accept: */*",
  })
  RedisUserDTO getUserFromRedis(@Param("id") @javax.annotation.Nonnull UUID id);

  /**
   * 
   * Similar to <code>getUserFromRedis</code> but it also returns the http response headers .
   * 
   * @param id  (required)
   * @return A ApiResponse that wraps the response boyd and the http headers.
   */
  @RequestLine("GET /api/users/{id}/redis")
  @Headers({
    "Accept: */*",
  })
  ApiResponse<RedisUserDTO> getUserFromRedisWithHttpInfo(@Param("id") @javax.annotation.Nonnull UUID id);



  /**
   * 
   * 
   * @param ids  (required)
   * @return List&lt;RedisUserDTO&gt;
   */
  @RequestLine("GET /api/users/redis/{ids}")
  @Headers({
    "Accept: */*",
  })
  List<RedisUserDTO> getUsersFromRedis(@Param("ids") @javax.annotation.Nonnull String ids);

  /**
   * 
   * Similar to <code>getUsersFromRedis</code> but it also returns the http response headers .
   * 
   * @param ids  (required)
   * @return A ApiResponse that wraps the response boyd and the http headers.
   */
  @RequestLine("GET /api/users/redis/{ids}")
  @Headers({
    "Accept: */*",
  })
  ApiResponse<List<RedisUserDTO>> getUsersFromRedisWithHttpInfo(@Param("ids") @javax.annotation.Nonnull String ids);



  /**
   * 
   * 
   * @param UUID  (required)
   * @return List&lt;RedisUserDTO&gt;
   */
  @RequestLine("POST /api/users/redis")
  @Headers({
    "Content-Type: application/json",
    "Accept: */*",
  })
  List<RedisUserDTO> getUsersFromRedisPost(@javax.annotation.Nonnull List<UUID> UUID);

  /**
   * 
   * Similar to <code>getUsersFromRedisPost</code> but it also returns the http response headers .
   * 
   * @param UUID  (required)
   * @return A ApiResponse that wraps the response boyd and the http headers.
   */
  @RequestLine("POST /api/users/redis")
  @Headers({
    "Content-Type: application/json",
    "Accept: */*",
  })
  ApiResponse<List<RedisUserDTO>> getUsersFromRedisPostWithHttpInfo(@javax.annotation.Nonnull List<UUID> UUID);



  /**
   * 
   * 
   * @param id  (required)
   * @param userDTO  (required)
   * @return UserDTO
   */
  @RequestLine("PATCH /api/users/{id}")
  @Headers({
    "Content-Type: application/json",
    "Accept: */*",
  })
  UserDTO partialUpdateUser(@Param("id") @javax.annotation.Nonnull UUID id, @javax.annotation.Nonnull UserDTO userDTO);

  /**
   * 
   * Similar to <code>partialUpdateUser</code> but it also returns the http response headers .
   * 
   * @param id  (required)
   * @param userDTO  (required)
   * @return A ApiResponse that wraps the response boyd and the http headers.
   */
  @RequestLine("PATCH /api/users/{id}")
  @Headers({
    "Content-Type: application/json",
    "Accept: */*",
  })
  ApiResponse<UserDTO> partialUpdateUserWithHttpInfo(@Param("id") @javax.annotation.Nonnull UUID id, @javax.annotation.Nonnull UserDTO userDTO);



  /**
   * 
   * 
   * @return Map&lt;String, Object&gt;
   */
  @RequestLine("POST /api/users/sync-to-redis")
  @Headers({
    "Accept: */*",
  })
  Map<String, Object> syncUsersToRedis();

  /**
   * 
   * Similar to <code>syncUsersToRedis</code> but it also returns the http response headers .
   * 
   * @return A ApiResponse that wraps the response boyd and the http headers.
   */
  @RequestLine("POST /api/users/sync-to-redis")
  @Headers({
    "Accept: */*",
  })
  ApiResponse<Map<String, Object>> syncUsersToRedisWithHttpInfo();



  /**
   * 
   * 
   * @param id  (required)
   * @param userDTO  (required)
   * @return UserDTO
   */
  @RequestLine("PUT /api/users/{id}")
  @Headers({
    "Content-Type: application/json",
    "Accept: */*",
  })
  UserDTO updateUser(@Param("id") @javax.annotation.Nonnull UUID id, @javax.annotation.Nonnull UserDTO userDTO);

  /**
   * 
   * Similar to <code>updateUser</code> but it also returns the http response headers .
   * 
   * @param id  (required)
   * @param userDTO  (required)
   * @return A ApiResponse that wraps the response boyd and the http headers.
   */
  @RequestLine("PUT /api/users/{id}")
  @Headers({
    "Content-Type: application/json",
    "Accept: */*",
  })
  ApiResponse<UserDTO> updateUserWithHttpInfo(@Param("id") @javax.annotation.Nonnull UUID id, @javax.annotation.Nonnull UserDTO userDTO);


}
