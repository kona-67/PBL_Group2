/*
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        // --- ⚙️ AWS RDS 接続情報の設定 ⚙️ ---
        // ⚠️ 下記の【  ]の部分をご自身のRDSの設定に書き換えてください
        //String url = "jdbc:mysql://【あなたのRDSエンドポイント】:3306/【設定したデータベース名】";
        String url = "jdbc:mysql://pbl-group-2.c74c442iwj4m.us-east-1.rds.amazonaws.com:3306/pbl_db"; // RDSのJDBC URL
        String user = "admin";  // RDSのユーザー名
        String password = "pbl-root"; // RDSのパスワード
        // -------------------------------------

        out.println("<html><head><title>DBテスト</title></head><body>");
        out.println("<h2>AWS RDS 接続テスト結果</h2>");

        // JDBCドライバのロードと接続確認
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(url, user, password)) {
                out.println("<h3 style='color: green;'>🎉 AWS RDSへの接続に成功しました！</h3>");
                out.println("<p>データベースと正常に通信できています。チーム開発の準備はバッチリです！</p>");
            }
        } catch (ClassNotFoundException e) {
            out.println("<h3 style='color: red;'>❌ JDBCドライバ（部品）が見つかりません</h3>");
            out.println("<pre>" + e.getMessage() + "</pre>");
        } catch (SQLException e) {
            out.println("<h3 style='color: red;'>❌ AWS RDSへの接続に失敗しました</h3>");
            out.println("<p>URL、パスワード、またはAWSのセキュリティグループ設定を確認してください。</p>");
            out.println("<pre>" + e.toString() + "</pre>");
        }

        out.println("<br><a href='index.html'>戻る</a>");
        out.println("</body></html>");
    }
}
*/