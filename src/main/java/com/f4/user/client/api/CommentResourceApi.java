package com.f4.user.client.api;

import com.f4.user.client.ApiClient;
import com.f4.user.client.EncodingUtils;
import com.f4.user.client.model.ApiResponse;

import com.f4.user.client.model.CommentDTO;
import java.util.UUID;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import feign.*;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2025-06-26T07:34:31.980696460+07:00[Asia/Ho_Chi_Minh]", comments = "Generator version: 7.13.0")
public interface CommentResourceApi extends ApiClient.Api {


  /**
   * 
   * 
   * @param commentDTO  (required)
   * @return CommentDTO
   */
  @RequestLine("POST /api/comments")
  @Headers({
    "Content-Type: application/json",
    "Accept: */*",
  })
  CommentDTO createComment(@javax.annotation.Nonnull CommentDTO commentDTO);

  /**
   * 
   * Similar to <code>createComment</code> but it also returns the http response headers .
   * 
   * @param commentDTO  (required)
   * @return A ApiResponse that wraps the response boyd and the http headers.
   */
  @RequestLine("POST /api/comments")
  @Headers({
    "Content-Type: application/json",
    "Accept: */*",
  })
  ApiResponse<CommentDTO> createCommentWithHttpInfo(@javax.annotation.Nonnull CommentDTO commentDTO);



  /**
   * 
   * 
   * @param id  (required)
   */
  @RequestLine("DELETE /api/comments/{id}")
  @Headers({
    "Accept: application/json",
  })
  void deleteComment(@Param("id") @javax.annotation.Nonnull UUID id);

  /**
   * 
   * Similar to <code>deleteComment</code> but it also returns the http response headers .
   * 
   * @param id  (required)
   */
  @RequestLine("DELETE /api/comments/{id}")
  @Headers({
    "Accept: application/json",
  })
  ApiResponse<Void> deleteCommentWithHttpInfo(@Param("id") @javax.annotation.Nonnull UUID id);



  /**
   * 
   * 
   * @param page Zero-based page index (0..N) (optional, default to 0)
   * @param size The size of the page to be returned (optional, default to 20)
   * @param sort Sorting criteria in the format: property,(asc|desc). Default sort order is ascending. Multiple sort criteria are supported. (optional)
   * @return List&lt;CommentDTO&gt;
   */
  @RequestLine("GET /api/comments?page={page}&size={size}&sort={sort}")
  @Headers({
    "Accept: */*",
  })
  List<CommentDTO> getAllComments(@Param("page") @javax.annotation.Nullable Integer page, @Param("size") @javax.annotation.Nullable Integer size, @Param("sort") @javax.annotation.Nullable List<String> sort);

  /**
   * 
   * Similar to <code>getAllComments</code> but it also returns the http response headers .
   * 
   * @param page Zero-based page index (0..N) (optional, default to 0)
   * @param size The size of the page to be returned (optional, default to 20)
   * @param sort Sorting criteria in the format: property,(asc|desc). Default sort order is ascending. Multiple sort criteria are supported. (optional)
   * @return A ApiResponse that wraps the response boyd and the http headers.
   */
  @RequestLine("GET /api/comments?page={page}&size={size}&sort={sort}")
  @Headers({
    "Accept: */*",
  })
  ApiResponse<List<CommentDTO>> getAllCommentsWithHttpInfo(@Param("page") @javax.annotation.Nullable Integer page, @Param("size") @javax.annotation.Nullable Integer size, @Param("sort") @javax.annotation.Nullable List<String> sort);


  /**
   * 
   * 
   * Note, this is equivalent to the other <code>getAllComments</code> method,
   * but with the query parameters collected into a single Map parameter. This
   * is convenient for services with optional query parameters, especially when
   * used with the {@link GetAllCommentsQueryParams} class that allows for
   * building up this map in a fluent style.
   * @param queryParams Map of query parameters as name-value pairs
   *   <p>The following elements may be specified in the query map:</p>
   *   <ul>
   *   <li>page - Zero-based page index (0..N) (optional, default to 0)</li>
   *   <li>size - The size of the page to be returned (optional, default to 20)</li>
   *   <li>sort - Sorting criteria in the format: property,(asc|desc). Default sort order is ascending. Multiple sort criteria are supported. (optional)</li>
   *   </ul>
   * @return List&lt;CommentDTO&gt;
   */
  @RequestLine("GET /api/comments?page={page}&size={size}&sort={sort}")
  @Headers({
  "Accept: */*",
  })
  List<CommentDTO> getAllComments(@QueryMap(encoded=true) GetAllCommentsQueryParams queryParams);

  /**
  * 
  * 
  * Note, this is equivalent to the other <code>getAllComments</code> that receives the query parameters as a map,
  * but this one also exposes the Http response headers
      * @param queryParams Map of query parameters as name-value pairs
      *   <p>The following elements may be specified in the query map:</p>
      *   <ul>
          *   <li>page - Zero-based page index (0..N) (optional, default to 0)</li>
          *   <li>size - The size of the page to be returned (optional, default to 20)</li>
          *   <li>sort - Sorting criteria in the format: property,(asc|desc). Default sort order is ascending. Multiple sort criteria are supported. (optional)</li>
      *   </ul>
          * @return List&lt;CommentDTO&gt;
      */
      @RequestLine("GET /api/comments?page={page}&size={size}&sort={sort}")
      @Headers({
    "Accept: */*",
      })
   ApiResponse<List<CommentDTO>> getAllCommentsWithHttpInfo(@QueryMap(encoded=true) GetAllCommentsQueryParams queryParams);


   /**
   * A convenience class for generating query parameters for the
   * <code>getAllComments</code> method in a fluent style.
   */
  public static class GetAllCommentsQueryParams extends HashMap<String, Object> {
    public GetAllCommentsQueryParams page(@javax.annotation.Nullable final Integer value) {
      put("page", EncodingUtils.encode(value));
      return this;
    }
    public GetAllCommentsQueryParams size(@javax.annotation.Nullable final Integer value) {
      put("size", EncodingUtils.encode(value));
      return this;
    }
    public GetAllCommentsQueryParams sort(@javax.annotation.Nullable final List<String> value) {
      put("sort", EncodingUtils.encodeCollection(value, "multi"));
      return this;
    }
  }

  /**
   * 
   * 
   * @param id  (required)
   * @return CommentDTO
   */
  @RequestLine("GET /api/comments/{id}")
  @Headers({
    "Accept: */*",
  })
  CommentDTO getComment(@Param("id") @javax.annotation.Nonnull UUID id);

  /**
   * 
   * Similar to <code>getComment</code> but it also returns the http response headers .
   * 
   * @param id  (required)
   * @return A ApiResponse that wraps the response boyd and the http headers.
   */
  @RequestLine("GET /api/comments/{id}")
  @Headers({
    "Accept: */*",
  })
  ApiResponse<CommentDTO> getCommentWithHttpInfo(@Param("id") @javax.annotation.Nonnull UUID id);



  /**
   * 
   * 
   * @param id  (required)
   * @param commentDTO  (required)
   * @return CommentDTO
   */
  @RequestLine("PATCH /api/comments/{id}")
  @Headers({
    "Content-Type: application/json",
    "Accept: */*",
  })
  CommentDTO partialUpdateComment(@Param("id") @javax.annotation.Nonnull UUID id, @javax.annotation.Nonnull CommentDTO commentDTO);

  /**
   * 
   * Similar to <code>partialUpdateComment</code> but it also returns the http response headers .
   * 
   * @param id  (required)
   * @param commentDTO  (required)
   * @return A ApiResponse that wraps the response boyd and the http headers.
   */
  @RequestLine("PATCH /api/comments/{id}")
  @Headers({
    "Content-Type: application/json",
    "Accept: */*",
  })
  ApiResponse<CommentDTO> partialUpdateCommentWithHttpInfo(@Param("id") @javax.annotation.Nonnull UUID id, @javax.annotation.Nonnull CommentDTO commentDTO);



  /**
   * 
   * 
   * @param id  (required)
   * @param commentDTO  (required)
   * @return CommentDTO
   */
  @RequestLine("PUT /api/comments/{id}")
  @Headers({
    "Content-Type: application/json",
    "Accept: */*",
  })
  CommentDTO updateComment(@Param("id") @javax.annotation.Nonnull UUID id, @javax.annotation.Nonnull CommentDTO commentDTO);

  /**
   * 
   * Similar to <code>updateComment</code> but it also returns the http response headers .
   * 
   * @param id  (required)
   * @param commentDTO  (required)
   * @return A ApiResponse that wraps the response boyd and the http headers.
   */
  @RequestLine("PUT /api/comments/{id}")
  @Headers({
    "Content-Type: application/json",
    "Accept: */*",
  })
  ApiResponse<CommentDTO> updateCommentWithHttpInfo(@Param("id") @javax.annotation.Nonnull UUID id, @javax.annotation.Nonnull CommentDTO commentDTO);


}
