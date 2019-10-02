

$(document).ready(function () {

    $("#search-form").submit(function (event) {

        //stop submit the form, we will post it manually.
        event.preventDefault();

        fire_ajax_submit();

    });

});

function fire_ajax_submit() {



    var search = {}
    search["username"] = $("#username").val();
    //search["email"] = $("#email").val();

    $("#btn-search").prop("disabled", true);

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/api/search",
        data: JSON.stringify(search),
        dataType: 'json',
        cache: false,
        timeout: 600000,

        success: function (data) {
            var dataItems = "";
            jQuery.each(data, function(index, value){
                  if(index==0) {
                      var json = "<h4>Ajax Response 1</h4><pre>" + JSON.stringify(value, null, 4) + "</pre>";
                      $('#feedback_1').html(
                          jQuery.getScript("js/drowChartWithInput_1.js")
                              .done(function() {
                                  console.log("yay, all good, do something *");
                                  drawFirstChart(value);
                              })
                              .fail(function() {
                                  console.log("boo first chart failed , fall back to something else");
                              })
                      );

                      //$('#feedback_1').html(json);
                      $("#btn-search").prop("disabled", false);
                 }
                 if(index==1){
                      var json = "<h4>Ajax Response 2</h4><pre>" + JSON.stringify(value, null, 4) + "</pre>";
                      //$.getScript("js/drowChartWithInput_1.js",function() {drawChart(value) });
                      $('#feedback_2').html(
                          jQuery.getScript("js/drowChartWithInput_2.js")
                              .done(function() {
                                  console.log("yay, all good, do something *");
                                  drawSecondChart(value);
                              })
                              .fail(function() {
                                  console.log("boo second chart failed, fall back to something else");
                              })
                      );

                     // $.getScript("drowChartWithInput_1.js");
                      $("#btn-search").prop("disabled", false);
                  }

            });

        },
        error: function (e) {

            var json = "<h4>Ajax Response</h4><pre>"
                + e.responseText + "</pre>";
            $('#feedback').html(json);

            console.log("ERROR : ", e);
            $("#btn-search").prop("disabled", false);

        }
    });

}