var myChartObject = document.getElementById('myChart');

var chart = new Chart(myChartObject, {
    type: 'horizontalBar',
    data: {
        labels: ["Analytical", "Anger", "Confident", "Fear", "Joy", "Sadness", "Tentative"],
        datasets: [{
            label: "average tone Kategorie",
            backgroundColor: 'rgba(255,0,0,0.4)',
            borderColor: 'rgba(255,0,0,1)',
            data: [0.781239 , 0, 0.702673, 0.583166, 0.587752, 0.581369, 0.760855]
        }]
    },
    options: {
        scales: {
            yAxes: [{
                ticks: {
                    beginAtZero: true
                }
            }]
        }
    }
})