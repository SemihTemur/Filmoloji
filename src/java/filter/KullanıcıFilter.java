/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package filter;

import entity.Admin;
import entity.Kullanıcı;
import jakarta.resource.cci.ResultSet;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import javax.swing.tree.RowMapper;

/**
 *
 * @author MERVAN
 */
@WebFilter("/*")
public class KullanıcıFilter implements Filter {

    @Override
    public void doFilter(ServletRequest sr, ServletResponse sr1, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) sr;
        HttpServletResponse res = (HttpServletResponse) sr1;

        String url = req.getRequestURI();

        HttpSession session = req.getSession(); // Mevcut oturumu almak için false

        Kullanıcı kullanıcı = null;

        if (session != null) {
            kullanıcı = (Kullanıcı) session.getAttribute("validUser");
        }

        if (kullanıcı == null) {

            if ((url.contains("KullaniciPaneli/Film") || url.contains("KullaniciPaneli/Dizi") || url.contains("TrenddekiDiziler") || url.contains("VizyondakiFilmler") || url.contains("Kitaplik") || url.contains("Iletisim"))) {
                res.sendRedirect(req.getContextPath()+"/KullaniciPaneli/girisYap.xhtml");
            } else {
                chain.doFilter(sr, sr1);
            }
        } else {
            chain.doFilter(sr, sr1);
        }

        /*
        if(session !=null){
            admin=(Admin) session.getAttribute("user");
        }
        if(admin==null && url.contains("Adminİşlemleri")){
            res.sendRedirect(req.getContextPath()+"/Admin.xhtml");           
        }
        else{
            chain.doFilter(sr, sr1);
        }
         */
    }
}
