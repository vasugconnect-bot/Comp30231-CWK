# Comp30231-CWK
CycleNest — Cloud-Based RESTful Rental Platform
A service-oriented rental platform built with Java JAX-RS (Jersey), MongoDB Atlas, and Apache Tomcat. Developed as part of the COMP30231 Cloud Computing coursework at Nottingham Trent University.
What it does
Users can browse rentable items, submit rental requests, cancel them, and check the real-world distance between their location and an item using the OpenRouteService routing API. All endpoints are secured with API key authentication and optimised for high-concurrency workloads.
Tech Stack

Java JAX-RS (Jersey) — REST API framework
MongoDB Atlas — Cloud-hosted PaaS database (BSON document storage)
OpenRouteService API — External routing for proximity calculation
Apache Tomcat — Application server
Jackson — JSON serialisation
Apache JMeter — Load testing and QoS evaluation

REST Endpoints
MethodEndpointDescriptionGET/webresources/itemsGet all items (in-memory cached)POST/webresources/requestsCreate a rental requestGET/webresources/requestsView all requests (admin)PUT/webresources/requests/{id}/cancelCancel a requestGET/webresources/distance?itemId=...&userLat=...&userLon=...Get distance to item
All requests require the header X-API-Key: cyclenest-secret-key
Security
API key authentication is enforced via a JAX-RS request filter (ApiKeyFilter.java). Any request missing a valid X-API-Key header receives HTTP 401 Unauthorized.
QoS Optimisation
In-memory caching was implemented on the GET /items endpoint to reduce MongoDB read load under high concurrency. Load tested with Apache JMeter — post-implementation results showed improved response times, higher throughput, and lower 99th percentile latency.
Quick Start
bash# View all items
curl http://localhost:8080/CWK/webresources/items -H "X-API-Key: cyclenest-secret-key"

# Create a rental request
curl -X POST http://localhost:8080/CWK/webresources/requests \
  -H "Content-Type: application/json" \
  -H "X-API-Key: cyclenest-secret-key" \
  -d '{"itemId":"item001","userId":"testUser","requestDate":"2025-03-25"}'

# Cancel a request
curl -X PUT http://localhost:8080/CWK/webresources/requests/REQ001/cancel \
  -H "X-API-Key: cyclenest-secret-key"

# Get distance to item
curl "http://localhost:8080/CWK/webresources/distance?itemId=item001&userLat=52.95&userLon=-1.15" \
  -H "X-API-Key: cyclenest-secret-key"
Project Structure
RESTSERVICE/
├── src/java/cyclenest/
│   ├── ItemResource.java        # GET items endpoint
│   ├── ItemsResource.java       # Items collection resource
│   ├── RequestsResource.java    # Rental requests endpoint
│   ├── DistanceResource.java    # Proximity calculation endpoint
│   ├── ApiKeyFilter.java        # API key security filter
│   ├── RateLimitFilter.java     # Rate limiting filter
│   ├── ORSClient.java           # OpenRouteService API client
│   ├── MongoDBUtil.java         # MongoDB connection utility
│   ├── ItemRepository.java      # Item data access layer
│   ├── RequestRepository.java   # Request data access layer
│   ├── Item.java                # Item model
│   └── RentalRequest.java       # Rental request model
Author
Vasu Goyal — Nottingham Trent UniversitySonnet 4.6 Low
