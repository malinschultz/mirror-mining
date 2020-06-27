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
import java.util.List;
import java.util.Map;

/*Diese Klasse setzt die articleDetail.html Seite
*
* Malin Schultz*/
@Controller
public class ArticleDetailController {
    @GetMapping("/articleDetail")
    public String articleDetail(@RequestParam(name = "articleDetail") String id, Model model) throws JSchException, SQLException, IOException {
        model.addAttribute("articleid", id);

        // Get documents and comments from the DB.
        DatabaseConnection db = new DatabaseConnection();
        List<Map<String, Object>> documents = db.getData("documents");
        List<Map<String, Object>> comments = db.getData("comments");

        for (Map<String, Object> document : documents) {
            if (document.get("id").toString().equals(id)) {
                String articleTitle = document.get("title").toString();
                String articleCategory = document.get("category").toString();
                model.addAttribute("articleTitle", articleTitle);
                model.addAttribute("articleCategory", articleCategory);
            }
        }

        List<String> commentList = new ArrayList<>();
        for (Map<String, Object> comment : comments) {
            if (comment.get("doc_id").toString().equals(id)) {
                commentList.add(comment.get("text").toString());
            }
        }
        model.addAttribute("articleComments", commentList);
        return "articleDetail";
    }
}
