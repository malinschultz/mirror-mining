package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.Article;
import com.example.servingwebcontent.DatabaseConnection;
import com.jcraft.jsch.JSchException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/*Diese Klasse setzt die articleDetail.html Seite
*
* Malin Schultz*/
@Controller
public class ArticleDetailController {
    @GetMapping("/articleDetail")
    public String articleDetail(@RequestParam(name = "articleDetail") String id, Model model) throws JSchException, SQLException, IOException {
        model.addAttribute("articleId", id);

        // Get doc title, doc category and doc comments from the DB.
        DatabaseConnection db = new DatabaseConnection();
        List<Map<String, Object>> articleTitle = db.executeQuery("select title from a_documents where id = " + id);
        model.addAttribute("articleTitle", articleTitle.get(0).get("title").toString());

        List<Map<String, Object>> articleCategory = db.executeQuery("select category from a_documents where id = " + id);
        model.addAttribute("articleCategory", articleCategory.get(0).get("category").toString());

        List<Map<String, Object>> comments = db.executeQuery("select id, user_id, text from a_comments where doc_id = " + id + "order by id asc");
        List<String> commentList = new ArrayList<>();
        for (Map<String, Object> comment : comments) {
            commentList.add(comment.get("text").toString());
        }
        model.addAttribute("articleComments", commentList);
        return "articleDetail";
    }
}
