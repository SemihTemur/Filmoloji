/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package util;

import bean.FilmveDiziBean;
import bean.TrenddekiDizilerBean;
import bean.VizyondakiFilmlerBean;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;


/**
 *
 * @author semih
 */
@WebServlet(name = "FileServlet", urlPatterns = {"/file/*"})
public class FileServlet extends HttpServlet {

    @Inject
    private FilmveDiziBean fb;

    @Inject
    private TrenddekiDizilerBean tb;
    
      @Inject
    private VizyondakiFilmlerBean vb;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String beanType = request.getParameter("beanType");
        String file = request.getPathInfo();
        File f = null;

        if ("TrenddekiDiziler".equals(beanType)) {
                 System.out.println("se");
            f = new File(tb.getUploadTo() + file);
        }
        else if("VizyondakiFilmler".equals(beanType)){
            System.out.println("se");
            f = new File(vb.getUploadTo() + file);
        }
        else {
            f = new File(fb.getUploadTo() + file);
        }

        Files.copy(f.toPath(), response.getOutputStream());
    }
   
}