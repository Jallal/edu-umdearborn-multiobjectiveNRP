google.charts.load("current", {packages: ["timeline"]});
google.charts.setOnLoadCallback(drawChart);

function drawChart() {

    var container = document.getElementById('timeline');
    var chart = new google.visualization.Timeline(container);
    var dataTable = new google.visualization.DataTable();
    dataTable.addColumn({type: 'string', id: 'Room'});
    dataTable.addColumn({type: 'string', id: 'Name'});
    dataTable.addColumn({type: 'date', id: 'Start'});
    dataTable.addColumn({type: 'date', id: 'End'});
    dataTable.addRows([
        ['Task 1', 'Jallal elhazzat', new Date(2019, 5, 1), new Date(2019, 5, 5)],
        ['Task 2', 'Ashley Snyder', new Date(2019, 5, 1), new Date(2019, 5, 10)],
        ['Task 3', 'Sri Padadda', new Date(2019, 5, 1), new Date(2019, 5, 12)],
        ['Task 4', 'Steve Snyder', new Date(2019, 5, 1), new Date(2019, 5, 6)],
        ['Task 5', 'Jallal elhazzat', new Date(2019, 5, 5), new Date(2019, 5, 12)],
        ['Task 6', 'Steve Snyder', new Date(2019, 5, 7), new Date(2019, 5, 15)]]
    );

    var options = {
        timeline: {colorByRowLabel: true}
    };

    chart.draw(dataTable, options);
}
