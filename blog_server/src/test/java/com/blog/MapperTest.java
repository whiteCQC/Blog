package com.blog;

import com.blog.mapper.*;
import com.blog.model.*;
import com.blog.util.PasswordUtil;
import com.blog.vo.CommentVo;
import com.blog.vo.MarkedMoveVo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MapperTest extends BlogApplicationTests {

    @Autowired
    ArticleMapper articleMapper;

    @Test
    void articleMapperTest(){
        int test = 1;
        Assertions.assertEquals(1,test);
        List<Article> list  = articleMapper.selectByUid(3);
        Assertions.assertEquals(5,list.size());

    }

    @Autowired
    UserMapper userMapper;
    @Test
    void userMapperTest(){
        User user =userMapper.getAuthor(1);
        Assertions.assertEquals("用户0",user.getUname());
        Assertions.assertEquals("164240@dxy.com",user.getEmail());
        Assertions.assertEquals(1,user.getUid());
        Assertions.assertEquals(PasswordUtil.toMD5("123456"),user.getPassword());
        User user1 = userMapper.selectByEmail("164240@dxy.com");
        Assertions.assertEquals("用户0",user1.getUname());
        Assertions.assertEquals("164240@dxy.com",user1.getEmail());
        Assertions.assertEquals(1,user1.getUid());
        Assertions.assertEquals(PasswordUtil.toMD5("123456"),user1.getPassword());
        User user2 = userMapper.selectByPrimaryKey(1);
        Assertions.assertEquals("用户0",user2.getUname());
        Assertions.assertEquals("164240@dxy.com",user2.getEmail());
        Assertions.assertEquals(1,user2.getUid());
        Assertions.assertEquals(PasswordUtil.toMD5("123456"),user2.getPassword());
    }

    @Autowired
    SpecialColumnMapper specialColumnMapper;
    @Test
    void spTest()
    {
        List<SpecialColumn> spList = specialColumnMapper.getSpecialColumnsByUid(1);
        Assertions.assertEquals(2,spList.size());
        Assertions.assertEquals("默认专栏",spList.get(0).getSpColName());
        Assertions.assertEquals(1,spList.get(0).getSpColId());
        Assertions.assertEquals(1,spList.get(0).getUid());
    }

    @Autowired
    MarkedMapper markedMapper;
    @Test
    void markMapper(){
        Marked marked = markedMapper.getMarked(1,"默认收藏夹");
        Assertions.assertEquals(1,marked.getUid());
        Assertions.assertEquals(1,marked.getMarkId());
        Assertions.assertEquals("默认收藏夹",marked.getMarkName());
        List<Marked> markedList = markedMapper.selectByUid(1);
        Assertions.assertEquals(5,markedList.size());
        Marked marked1 = markedMapper.getMarkedArticles(1,1);
        Assertions.assertEquals(1,marked1.getUid());
        Assertions.assertEquals(1,marked1.getMarkId());
        Assertions.assertEquals("默认收藏夹",marked1.getMarkName());
        List<Article> articles = marked1.getArticleList();
        Assertions.assertEquals(1,articles.size());
        int markId = markedMapper.getMarkId(1,"插入测试3");
        Assertions.assertEquals(2,markId);

    }

    @Autowired
    MarkedArticleMapper markedArticleMapper;
    @Test
    void markedArticleMapperTest(){
        MarkedMoveVo moveVo = new MarkedMoveVo(1,1,1,2);
        int row = markedArticleMapper.moveMarkedArticle(moveVo);
        Assertions.assertEquals(1,row);
        MarkedMoveVo moveVo1 = new MarkedMoveVo(1,1,2,1);
        row = markedArticleMapper.moveMarkedArticle(moveVo1);
        Assertions.assertEquals(1,row);

    }

    @Autowired
    CommentMapper commentMapper;
    @Test
    void commentMapperTest(){
        List<CommentVo> commentsOfArticle = commentMapper.getCommentsOfArticle(1);
        CommentVo commentVo = commentsOfArticle.get(0);
        Assertions.assertEquals(1,commentVo.getUid());
        Assertions.assertEquals("用户0",commentVo.getUserName());
        Assertions.assertEquals("第一条评论",commentVo.getCommentContent());
    }


}
