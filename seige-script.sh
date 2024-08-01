# Run siege with different concurrency (10, 25, 50, 100)
# Find resource availability, avg response time, throughput

# d - delay, c - concurrency, v - verbose (print messages), t - time for testing, f - file with urls
siege -d1  -c50  -v -t15s -f ./siege-urls/urls.txt