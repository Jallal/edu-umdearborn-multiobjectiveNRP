function activityChartLoading() {

    var activityChart = new Chart(document.getElementById("bubble-chart"), {
        type: 'horizontalBar',
        data: [{x: '2016-12-25', y: 20}, {x: '2016-12-26', y: 10}],
        options: {
            scales: {
                xAxes: [{
                    stacked: true
                }],
                yAxes: [{
                    stacked: true
                }]
            }
        }
    });
}
