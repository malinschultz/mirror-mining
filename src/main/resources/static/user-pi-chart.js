var myChartObject = document.getElementById('piChart');

var chart = new Chart(myChartObject, {
    type: 'horizontalBar',
    data: {
        labels: ["Openness", "Conscientiousness", "Extraversion", "Agreeableness", "Neuroticism"],
        datasets: [{
            label: "personality insights",
            backgroundColor: 'rgba(255,243,0,0.4)',
            borderColor: 'rgb(255,243,0,1)',
            data: [0.54245 , 0, 0.45234, 0.89712, 0.67323]
        }, {
            label: "average personality insights",
            fill: true,
            backgroundColor: 'rgba(0,255,255,0.4)',
            borderColor: 'rgb(0,255,255,1)',
            data: [0.79823, 0.21345, 0.92300, 0.31234, 0.45632]
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