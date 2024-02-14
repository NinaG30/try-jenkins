const request = require('supertest'); // Importing supertest to send HTTP requests
const app = require('./src/app'); // Importing your Express application

describe('GET /', () => {
  it('responds with "Hello World!"', async () => {
    // Sending a GET request to the root URL
    const response = await request(app).get('/');

    // Asserting that the response status is 200 OK
    expect(response.status).toBe(200);

    // Asserting that the response body contains "Hello World!"
    expect(response.text).toBe('Hello World!');
  });
});