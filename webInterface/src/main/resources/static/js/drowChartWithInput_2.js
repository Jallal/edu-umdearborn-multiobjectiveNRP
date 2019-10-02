var value = [['Task 1', 'Jallal elhazzat', new Date(2019, 5, 1), new Date(2019, 5, 5)],
    ['Task 2', 'Ashley Snyder', new Date(2019, 5, 1), new Date(2019, 5, 10)],
    ['Task 3', 'Sri Padadda', new Date(2019, 5, 1), new Date(2019, 5, 12)],
    ['Task 4', 'Steve Snyder', new Date(2019, 5, 1), new Date(2019, 5, 6)],
    ['Task 5', 'Jallal elhazzat', new Date(2019, 5, 5), new Date(2019, 5, 12)],
    ['Task 6', 'Steve Snyder', new Date(2019, 5, 7), new Date(2019, 5, 15)]];


google.charts.load("current", {packages: ["timeline"]});
google.charts.setOnLoadCallback(drawSecondChart);

function drawSecondChart(value) {
    if (value !== null) {
    // loop the outer array
    for (var i = 0; i < value.length; i++) {
        value[i][2] = new Date(value[i][2]);
        value[i][3] = new Date(value[i][3]);
    }
    console.log("*****************************I'm number 2 and I WAS CALLED*********************************");
    console.log(value);
    console.log("**************************************************************");
    var container = document.getElementById('timeline_2');
    var chart = new google.visualization.Timeline(container);
    var dataTable = new google.visualization.DataTable();
    dataTable.addColumn({type: 'string', id: 'Room'});
    dataTable.addColumn({type: 'string', id: 'Name'});
    dataTable.addColumn({type: 'date', id: 'Start'});
    dataTable.addColumn({type: 'date', id: 'End'});
    dataTable.addRows(value);

    var options = {
        timeline: {colorByRowLabel: true}
    };

    chart.draw(dataTable, options);
}
}
