import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// URLが「/hello」でアクセスされたら、このJavaが動くという設定です
@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
    
    // HTMLの form(method="post") から送られてきたデータを受け取るメソッド
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // 1. 日本語の文字化けを防ぐおまじない
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        
        // 2. フロントエンド(HTML)の「name="userName"」に入力されたデータを受け取る！
        String name = request.getParameter("userName");
        
        // 3. ブラウザ(画面)に結果を返す処理
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head><meta charset='UTF-8'><title>結果</title></head>");
        out.println("<body>");
        out.println("<h2>バックエンド（Java）からの返信です！</h2>");
        out.println("<p>こんにちは、<b>" + name + "</b> さん！</p>");
        out.println("<p>HTMLとJavaの連携が大成功しました 🎉</p>");
        out.println("<a href='index.html'>戻る</a>");
        out.println("</body>");
        out.println("</html>");
    }
}