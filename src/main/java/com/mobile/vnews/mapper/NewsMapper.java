package com.mobile.vnews.mapper;


import com.mobile.vnews.module.bean.News;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public  interface NewsMapper {


    /**
     * Add likeCount and viewCount
     * @param start
     * @param count
     * @return
     */
    @Select("SELECT ID, title, author, description, image, publishedAt, source, content, level, type,\n" +
            "  (SELECT count(newsID) FROM like_news WHERE newsID = news.ID) as likeCount,\n" +
            "  (SELECT count(newsID) FROM view_news WHERE newsID = news.ID) as viewCount,\n" +
            "  (SELECT count(newsID) FROM comment WHERE newsID = news.ID) as commentCount\n" +
            "  FROM news limit #{start}, #{count}")
    List<News> getNews(int start, int count);

    /**
     * Add likeCount and viewCount
     *
     * @return
     */
    @Select("SELECT ID, title, author, description, image, publishedAt, source, content, level, type,\n" +
            "  (SELECT count(newsID) FROM like_news WHERE newsID = news.ID) as likeCount,\n" +
            "  (SELECT count(newsID) FROM view_news WHERE newsID = news.ID) as viewCount,\n" +
            "  (SELECT count(newsID) FROM comment WHERE newsID = news.ID) as commentCount\n" +
            "  FROM news")
    List<News> getAllNews();
    /**
     *
     * @param type
     * @return
     */
    @Select("SELECT ID, title, author, description, image, publishedAt, source, content, level, type,\n" +
            "  (SELECT count(newsID) FROM like_news WHERE newsID = news.ID) as likeCount,\n" +
            "  (SELECT count(newsID) FROM view_news WHERE newsID = news.ID) as viewCount,\n" +
            "  (SELECT count(newsID) FROM comment WHERE newsID = news.ID) as commentCount\n" +
            "  FROM news WHERE type = #{type}")
    List<News> getAllNewsByType(String type);

    /**
     *
     * @param type
     * @param start
     * @param count
     * @return
     */
    @Select("SELECT ID, title, author, description, image, publishedAt, source, content, level, type,\n" +
            "  (SELECT count(newsID) FROM like_news WHERE newsID = news.ID) as likeCount,\n" +
            "  (SELECT count(newsID) FROM view_news WHERE newsID = news.ID) as viewCount,\n" +
            "  (SELECT count(newsID) FROM comment WHERE newsID = news.ID) as commentCount\n" +
            "  FROM news WHERE type = #{type} limit #{start}, #{count}")
    List<News> getNewsByType(String type, int start, int count);

    /**
     * Add likeCount and viewCount
     * @param count
     * @return
     */
    @Select("SELECT ID, title, author, description, image, publishedAt, source, content, level, type,\n" +
            "  ((SELECT count(newsID) FROM like_news WHERE newsID = news.ID) + \n" +
            "  (SELECT count(newsID) FROM view_news WHERE newsID = news.ID) +  \n" +
            "  (SELECT count(newsID) FROM comment WHERE newsID = news.ID)) as rank\n" +
            "  FROM news ORDER BY rank DESC limit #{count}")
    List<News> getHotNews(int count);

    /**
     * Add likeCount and viewCount
     * @param ID
     * @return
     */
    @Select("SELECT ID, title, author, description, image, publishedAt, source, content, level, type,\n" +
            "  (SELECT count(newsID) FROM like_news WHERE newsID = news.ID) as likeCount,\n" +
            "  (SELECT count(newsID) FROM view_news WHERE newsID = news.ID) as viewCount,\n" +
            "  (SELECT count(newsID) FROM comment WHERE newsID = news.ID) as commentCount\n" +
            "  FROM news WHERE ID = #{ID}")
    News getNewsByID(int ID);

    /**
     * Add likeCount and viewCount
     * @param userID
     * @return
     */
    @Select("SELECT ID, title, author, description, image, publishedAt, source, content, level, type,\n" +
            "  (SELECT count(newsID) FROM like_news WHERE newsID = news.ID) as likeCount,\n" +
            "  (SELECT count(newsID) FROM view_news WHERE newsID = news.ID) as viewCount,\n" +
            "  (SELECT count(newsID) FROM comment WHERE newsID = news.ID) as commentCount\n" +
            "  FROM news, like_news WHERE ID = like_news.newsID AND userID = #{userID}")
    List<News> getFavoriteNewsByUserID(String userID);

    @Insert("INSERT INTO like_news (userID, newsID) VALUES (#{arg0}, #{arg1})")
    void addFavoriteNews(String userID, int newsID);

    @Select("SELECT count(*) FROM like_news WHERE userID = #{arg0} AND newsID = #{arg1}")
    int checkFavoriteNews(String userID, int newsID);

    @Delete("DELETE FROM like_news WHERE userID = #{arg0} AND newsID = #{arg１}")
    void deleteFavoriteNews(String userID, int newsID);

    @Insert("INSERT INTO view_news (userID, newsID) VALUES (#{arg0}, #{arg1})")
    void addViewNews(String userID, int newsID);

    /**
     * Add likeCount and viewCount
     * @param userID
     * @return
     */
    @Select("SELECT ID, title, author, description, image, publishedAt, source, content, level, type,\n" +
            "  (SELECT count(newsID) FROM like_news WHERE newsID = news.ID) as likeCount,\n" +
            "  (SELECT count(newsID) FROM view_news WHERE newsID = news.ID) as viewCount,\n" +
            "  (SELECT count(newsID) FROM comment WHERE newsID = news.ID) as commentCount\n" +
            "FROM news, like_news WHERE ID = view_news.newsID AND userID = #{userID}")
    List<News> getViewNewsByUserID(String userID);
}

