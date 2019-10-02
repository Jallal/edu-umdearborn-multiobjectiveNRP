google.charts.load("current", {packages: ["timeline"]});
google.charts.setOnLoadCallback(drawChart);

function drawChart() {
    var args = '';

    var container = document.getElementById('timeline');
    var chart = new google.visualization.Timeline(container);
    var dataTable = new google.visualization.DataTable();
    dataTable.addColumn({type: 'string', id: 'Room'});
    dataTable.addColumn({type: 'string', id: 'Name'});
    dataTable.addColumn({type: 'date', id: 'Start'});
    dataTable.addColumn({type: 'date', id: 'End'});
    dataTable.addRows(

    );

    var options = {
        timeline: {colorByRowLabel: true}
    };

    chart.draw(dataTable, options);
}
