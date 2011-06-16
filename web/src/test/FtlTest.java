package test;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@SuppressWarnings("serial")
public class FtlTest extends HttpServlet
{
    private Configuration cfg = null;

    @Override
    public void init() throws ServletException
    {
        cfg = new Configuration();
        cfg.setServletContextForTemplateLoading(this.getServletContext(), null);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {
        // 模板文件会根据key，读取value
        Map root = new HashMap();
        root.put("message", "您好！");
        root.put("username", "编程爱好者");
        Template t = cfg.getTemplate("templates/test.ftl");
        PrintWriter out = resp.getWriter();
        try
        {
            t.process(root, out);
        } catch (TemplateException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {
        this.doPost(req, resp);
    }

}