package com.edu.umich.ISELab.webinterface.controller;

import com.edu.umich.ISELab.webinterface.model.AjaxResponseBody;
import com.edu.umich.ISELab.webinterface.model.SearchCriteria;
import com.edu.umich.ISELab.webinterface.services.UserService;
import edu.umich.ISELab.core.backlog.WorkItem;
import edu.umich.ISELab.core.grooming.Grooming;
import edu.umich.ISELab.execution.ExploreWebUI;
import edu.umich.ISELab.optimization.solution.GroomingSolution;
import edu.umich.ISELab.optimization.solution.Solution;
import edu.umich.ISELab.optimization.variables.GroomingVariable;
import edu.umich.ISELab.optimization.variables.Variable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class SearchController {

    UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/api/search")
    public ResponseEntity<?> getSearchResultViaAjax(@Valid @RequestBody SearchCriteria search, Errors errors) throws Exception {

        AjaxResponseBody result = new AjaxResponseBody();

        //If error, just return a 400 bad request, along with the error message
        if (errors.hasErrors()) {

            result.setMsg(errors.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(",")));
            return ResponseEntity.badRequest().body(result);

        }

        /*List<User> users = userService.findByUserNameOrEmail(search.getUsername());
        if (users.isEmpty()) {
            result.setMsg("no user found!");
        } else {
            result.setMsg("success");
        }*/


        //result.setResult(users);

        // ResponseEntity.ok(result);


        result.setMsg("success");


        //Our Taks
       /* data[0][0] = "Task 1";
        data[1][0] = "Task 2";
        data[2][0] = "Task 3";
        data[3][0] = "Task 4";
        data[4][0] = "Task 5";
        data[5][0] = "Task 6";
        data[6][0] = "Task 7";
        data[7][0] = "Task 8";
        data[8][0] = "Task 9";


        //People
        data[0][1] = "Jallal elhazzat";
        data[1][1] = "Ashley Snyder";
        data[2][1] = "Sri Padadda";
        data[3][1] = "Steve Snyder";
        data[4][1] = "John DOe";
        data[5][1] = "Ed Doe";
        data[6][1] = "Moe Ali";
        data[7][1] = "Stephan Moe";
        data[8][1] = "Steve Snyder";

        // X Axis timeline

        data[0][2] = String.valueOf(new Date(2019, 5, 1));
        data[1][2] = String.valueOf(new Date(2019, 5, 1));
        data[2][2] = String.valueOf(new Date(2019, 5, 1));
        data[3][2] = String.valueOf(new Date(2019, 5, 1));
        data[4][2] = String.valueOf(new Date(2019, 5, 1));
        data[5][2] = String.valueOf(new Date(2019, 5, 1));
        data[6][2] = String.valueOf(new Date(2019, 5, 1));
        data[7][2] = String.valueOf(new Date(2019, 5, 1));
        data[8][2] = String.valueOf(new Date(2019, 5, 1));


        // Y Axis timeline
        data[0][3] = String.valueOf(new Date(2019, 5, 5));
        data[1][3] = String.valueOf(new Date(2019, 5, 10));
        data[2][3] = String.valueOf(new Date(2019, 5, 12));
        data[3][3] = String.valueOf(new Date(2019, 5, 6));
        data[4][3] = String.valueOf(new Date(2019, 5, 12));
        data[5][3] = String.valueOf(new Date(2019, 5, 15));
        data[6][3] = String.valueOf(new Date(2019, 5, 15));
        data[7][3] = String.valueOf(new Date(2019, 5, 10));
        data[8][3] = String.valueOf(new Date(2019, 5, 10));*/


        ExploreWebUI webUI = new ExploreWebUI();
        List<Solution> solutionForWeb = webUI.getSolutionForWeb();
        List<String[][]> solutionArrays = new ArrayList();

        try {
            for (int i = 0; i < solutionForWeb.size(); i++) {
                String[][] data = new String[9][4];
                GroomingSolution solution = (GroomingSolution) solutionForWeb.get(i);
                List<Variable> variablesList = solution.getVariables();
                GroomingVariable variable = (GroomingVariable) variablesList.get(0);
                List<Grooming> grooming = variable.getRefactorings();

                int d = 0;
                for (Grooming groum : grooming) {
                    for (WorkItem item : groum.getWorkItems()) {
                        if (d < 9) {
                            data[d][0] = item.getWorkItemID();
                            data[d][1] = item.getPerson().getName();
                            data[d][2] = String.valueOf(new Date(2019, 5, 1));
                            data[d][3] = String.valueOf(new Date(2019, 5, 10));
                            d++;
                        }
                    }
                    d=0;
                }
                solutionArrays.add(data);
            }
        } catch (Exception e) {
            System.out.println("*****************************************************************************");
            System.out.println(e);
            System.out.println("*****************************************************************************");

        }


        result.setResult(solutionArrays);
        return ResponseEntity.ok(solutionArrays);

    }

}
