import http from 'k6/http';
import {check} from 'k6';

export let options = {
    thresholds: {
        http_req_failed: ['rate<0.01'],
        http_req_duration: ['p(95)<1000'],  // allowing 95 percentile of requests to be under 1 second
    },
    vus: 10,
    duration: '30s',
};

export default function () {
    for (let i = 1; i <= 7; i++) {
        let res = http.get(`http://localhost:8080/api/directors?threshold=${i}`);
        check(res, {
            'status was 200': (r) => r.status == 200,
            'transaction time OK': (r) => r.timings.duration < 500,
        });
    }
}