package com.test.web.application.first.springbootfirstwebapplication.Controllers;

import com.test.web.application.first.springbootfirstwebapplication.Models.Todo;
import com.test.web.application.first.springbootfirstwebapplication.Services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.naming.Binding;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@SessionAttributes("name")
public class TodoController {

    @Autowired
    TodoService service;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(
                dateFormat, false));
    }

    @RequestMapping(value="/list-todos", method = RequestMethod.GET)
    public String showTodos(ModelMap map)
    {
        String uname = getLoggedInuserName(map);
        map.put("todos", service.retrieveTodos(uname));
        return "list-todos";
    }

    @RequestMapping(value="/add-todo", method = RequestMethod.GET)
    public String addTodo(ModelMap map)
    {
        map.addAttribute("todo", new Todo(0,(String)map.get("name"),"",new Date(),false));
        return "add-todo";
    }

    @RequestMapping(value="/add-todo", method = RequestMethod.POST)
    // whenever we want to have a validation we put the value on the field and add @valid attribute
    // the validation result is shown on BindingResult attribute
    public String addTodoPost(ModelMap map, @Valid Todo todo, BindingResult result)
    {
        if(result.hasErrors())
        {
            return "add-todo";
        }
        service.addTodo(getLoggedInuserName(map),todo.getDesc(), new Date(), false);
        return "redirect:/list-todos";
    }

    @RequestMapping(value="/delete-todo", method = RequestMethod.GET)
    public String deleteTodo(@RequestParam int id)
    {
        service.deleteTodo(id);
        return "redirect:/list-todos";
    }

    @RequestMapping(value="/update-todo", method = RequestMethod.GET)
    public String updateTodo(ModelMap map, @RequestParam int id)
    {
        Todo todo = service.retrieveTodos(id);
        map.put("todo",todo);
        return "/add-todo";
    }

    @RequestMapping(value="/update-todo", method = RequestMethod.POST)
    public String updateTodo(ModelMap map, @Valid Todo todo, BindingResult result)
    {
        todo.setUser(getLoggedInuserName(map));
        if(result.hasErrors())
        {
            return "add-todo";
        }
        service.updateTodos(todo);
        return "redirect:/list-todos";
    }

    private String getLoggedInuserName(ModelMap map) {
        return (String) map.get("name");
    }

}
