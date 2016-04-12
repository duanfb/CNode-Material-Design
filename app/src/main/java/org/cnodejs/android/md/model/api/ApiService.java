package org.cnodejs.android.md.model.api;

import org.cnodejs.android.md.model.entity.LoginInfo;
import org.cnodejs.android.md.model.entity.Notification;
import org.cnodejs.android.md.model.entity.Result;
import org.cnodejs.android.md.model.entity.TabType;
import org.cnodejs.android.md.model.entity.Topic;
import org.cnodejs.android.md.model.entity.TopicUpInfo;
import org.cnodejs.android.md.model.entity.TopicWithReply;
import org.cnodejs.android.md.model.entity.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    //=====
    // 主题
    //=====

    @GET("topics")
    Call<Result.Data<List<Topic>>> getTopicList(
            @Query("tab") TabType tab,
            @Query("page") Integer page,
            @Query("limit") Integer limit,
            @Query("mdrender") Boolean mdrender
    );

    @GET("topic/{topicId}")
    Call<Result.Data<TopicWithReply>> getTopic(
            @Query("accesstoken") String accessToken,
            @Path("topicId") String topicId,
            @Query("mdrender") Boolean mdrender
    );
    
    @POST("topics")
    @FormUrlEncoded
    Call<Result.CreateTopic> createTopic(
            @Field("accesstoken") String accessToken,
            @Field("tab") TabType tab,
            @Field("title") String title,
            @Field("content") String content
    );

    //=========
    // 主题收藏
    //=========

    @POST("topic_collect/collect")
    @FormUrlEncoded
    Call<Result> collectTopic(
            @Field("accesstoken") String accessToken,
            @Field("topic_id") String topicId
    );

    @POST("topic_collect/de_collect")
    @FormUrlEncoded
    Call<Result> decollectTopic(
            @Field("accesstoken") String accessToken,
            @Field("topic_id") String topicId
    );

    @GET("topic_collect/{loginName}")
    Call<Result.Data<List<Topic>>> getCollectTopicList(
            @Path("loginName") String loginName
    );

    //=====
    // 评论
    //=====

    @POST("topic/{topicId}/replies")
    @FormUrlEncoded
    Call<Result.ReplyTopic> replyTopic(
            @Field("accesstoken") String accessToken,
            @Path("topicId") String topicId,
            @Field("content") String content,
            @Field("reply_id") String replyId
    );

    @POST("reply/{replyId}/ups")
    @FormUrlEncoded
    Call<TopicUpInfo> upTopic(
            @Field("accesstoken") String accessToken,
            @Path("replyId") String replyId
    );

    //=====
    // 用户
    //=====

    @GET("user/{loginName}")
    Call<Result.Data<User>> getUser(
            @Path("loginName") String loginName
    );

    @POST("accesstoken")
    @FormUrlEncoded
    Call<LoginInfo> accessToken(
            @Field("accesstoken") String accessToken
    );

    //=========
    // 消息通知
    //=========

    @GET("message/count")
    Call<Result.Data<Integer>> getMessageCount(
            @Query("accesstoken") String accessToken
    );

    @GET("messages")
    Call<Result.Data<Notification>> getMessages(
            @Query("accesstoken") String accessToken,
            @Query("mdrender") Boolean mdrender
    );

    @POST("message/mark_all")
    @FormUrlEncoded
    Call<Result> markAllMessageRead(
            @Field("accesstoken") String accessToken
    );

}
