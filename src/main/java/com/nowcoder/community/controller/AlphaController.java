package com.nowcoder.community.controller;

import com.nowcoder.community.service.AlphaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@Controller
// @RequestMapping 来映射请求，也就是通过它来指定控制器可以处理哪些URL请求
@RequestMapping("/alpha")
public class AlphaController {

    @RequestMapping("/hello")
//@responseBody注解的作用是将controller的方法返回的对象通过适当的转换器转换为指定的格式之后，写入到response对象的body区，通常用来返回JSON数据或者是XML数据。
//在使用此注解之后不会再走视图处理器，而是直接将数据写入到输入流中，他的效果等同于通过response对象输出指定格式的数据。
    @ResponseBody
    public String sayHello(){
        return "hello spring boot";
    }
    //Controller调用service，需要获得service层的bean->通过bean的方式：
    @Autowired
    private AlphaService alphaService;

    @RequestMapping("/data")
    @ResponseBody
    public String getData(){
        return alphaService.find();
    }

    @RequestMapping("/http")
    public void http(HttpServletRequest request, HttpServletResponse response){
        //获取请求方法
        System.out.println(request.getMethod());
        //请求路径
        System.out.println(request.getServletPath());
        //得到请求行的key（请求行是key-value格式）,返回值是一个迭代器Enumeration(比较老了。可以想象成iterator)
        Enumeration<String> enumeration=request.getHeaderNames();
        //迭代得到key-value
        while(enumeration.hasMoreElements()){
            String name = enumeration.nextElement();
            String value=request.getHeader(name);
            System.out.println(name+":"+value);
        }
        //得到请求体，包含的参数用getParameter方法
        System.out.println(request.getParameter("code"));


        //返回响应数据：response是用来向浏览器作出响应的对象，给浏览器返回数据信息
        //首先设置返回数据类型
        //返回一个网页类型时
        response.setContentType("text/html;Charset=utf-8");
        //以流的方式向浏览器输出,getWriter()方法获取输出流
        try(//java7 新语法，可自动调用close方法关闭流
                PrintWriter writer = response.getWriter();
                ){
            writer.write("<h1>牛客网</h1>");
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    //两种从页面传参到controller的方式：一种是？,一种是/
    //简便方法
    //①GET请求
    //场景：浏览器传过来的数据要求查询所有学生信息，并分页显示（传当前页数和每页的最大容量），格式如下：
    //   /students?current=1&limit=20
    //后面的method表示只能处理get请求，明确请求方式
    @RequestMapping(path = "/students",method = RequestMethod.GET)
    @ResponseBody
    //@RequestParam对参数的注入作更详细的声明，required表示默认是否传。以下表示可以没有current参数，如果没有，则默认为1
    public String getStudents(
            @RequestParam(name = "current",required = false, defaultValue = "1") int current,
            @RequestParam(name = "limit",required = false, defaultValue = "10")int limit){
        System.out.println(current);
        System.out.println(limit);
        return "some students";
    }
    //根据学生的id查询一个学生的信息：格式为：/student/123
    @RequestMapping(path = "/student/{id}",method = RequestMethod.GET)
    @ResponseBody
    //@PathVariable路径变量：从路径中得到变量赋值给参数
    public String getStudent(@PathVariable("id") int id){
        System.out.println(id);
        return "a student";
    }


    //浏览器向服务器提交数据时的处理方式：POST请求
    //Get传参时，参数会显示在url上，是明面上的，并且有长度限制，通常提交数据是用post请求
    //直接声明参数，参数名字与表单中的名字一致即可获取到post传过来的数据
    @RequestMapping(path = "/student",method = RequestMethod.POST)
    @ResponseBody
    public String saveStudent(String name,int age){
        System.out.println(name);
        System.out.println(age);
        return "success";
    }

    //如何向浏览器响应动态的HTML
    @RequestMapping(path = "/teacher" , method = RequestMethod.GET)
    //第一种
    public ModelAndView getTeacher(){
        ModelAndView mav = new ModelAndView();
        mav.addObject("name","张三");
        mav.addObject("age",30);
        mav.setViewName("/demo/view");
        return mav;
    }
    //第二种方式,更简洁
    @RequestMapping(path = "/school" , method = RequestMethod.GET)
    public String getSchool(Model model){
        model.addAttribute("name","中南大学");
        model.addAttribute("age",21);
        return "/demo/view";
    }

    //向浏览器响应json数据，在异步请求时（例子：注册时输入昵称后直接判断昵称是否被占用,网页不刷新时访问服务器）
    //Java对象 -->json字符串-->JS对象
    @RequestMapping(path = "/emp" , method = RequestMethod.GET)
    //以下注解会自动将返回值转换成JSON
    @ResponseBody
    public Map<String,Object> getEmp(){
        Map<String,Object> emp=new HashMap<>();
        emp.put("name","张三");
        emp.put("age",23);
        emp.put("salary",8000.00);
        return emp;
    }


    @RequestMapping(path = "/emps" , method = RequestMethod.GET)
    //以下注解会自动将返回值转换成JSON
    @ResponseBody
    public List<Map<String,Object>> getEmps(){
        List<Map<String,Object>> list=new ArrayList<>();
        Map<String,Object> emp=new HashMap<>();
        emp.put("name","张三");
        emp.put("age",23);
        emp.put("salary",8000.00);
        list.add(emp);
        emp=new HashMap<>();
        emp.put("name","李四");
        emp.put("age",63);
        emp.put("salary",18800.00);
        list.add(emp);
        return list;
    }

}
