## Observepoint coding challenge for Pedro Carara

___
**As an additional challenge, I built a web service using AWS Lambda to run this solution. 
The code for the web service can be found in this [repository](https://github.com/cararax/IpTrackerService).** 
___

### How does your code work?

To solve the challenge, I used a HashMap to store the IP addresses and their access counts, and a MinHeap to keep track of the IP addresses with the highest access counts.

The solution works as follows:

`requestHandled(ip_address)`: This method is responsible for receiving an IP address and updating the data structures in memory. It consists of two steps:

First, the method inserts the IP address and its access count into a HashMap. If the IP address already exists in the HashMap, the method increments its access count. If the IP address is not present, a new entry with that IP address is added.

Second, the method updates the MinHeap, which keeps the IP addresses sorted in ascending order based on their access count. If the IP address is already present in the MinHeap, the existing entry is removed. If there is space available in the MinHeap, the new IP address is added. Otherwise, if the access count of the new IP address is greater than the access count of the least frequent IP address in the MinHeap, the least frequent IP address is removed and replaced with the new IP address.

`top100()`: This method returns a list of key-value pairs containing the IP addresses and their access counts. It returns data from the MinHeap in descending order based on the access count.

`clear()`: This method clears the data structures and should be called at the beginning of each day. It resets the data structures by simply invoking the clear method on each of them, preparing the program for a new day of request tracking.

### What is the runtime complexity of each function?

- `requestHandled(String ipAddress)`: The complexity of this method is the insertion of the IP into the HashMap with complexity O(1) and the insertion into the MinHeap with complexity O(log n), where n is the size of the MinHeap limited to 100 entries.
- `top100()`: The complexity of this method is O(1) because it returns a list of the top 100 IP addresses in descending order.
- `clear()`: The complexity of this method is O(1) because it simply removes all elements from the HashMap and MinHeap.

### What other approaches did you decide not to pursue?

I've considered using only HashMap to solve the challenge, but I've found that they are too slow for sorting, and I've also considered using TreeMap, but I've found that they would use lots of memory and their sorting time did not meet the performance requirements.

Finally, I found that combining a HashMap and a MinHeap was a good way to get the job done. The insertion time is really fast, and it already keeps the list of the most accessed IPs sorted, which makes the top100 function that returns the sorted IPs super fast.

### How would you test this?

In order to test the solution, we could use a couple of different types of tests to make sure that the solution meets the requirements of the challenge.

Unit testing could be used with different test scenarios and edge cases to verify that the requestHandled, top100, and clear methods work correctly.

Performance testing could be used to ensure that the functions meet the speed requirements of the challenge, especially in the `top100()` method. This could include runtime measurements for different dataset sizes and an assessment of memory usage.

And lastly, integration testing could be used to simulate real-world use of the solution in a web service and ensure that it works correctly in a production environment.

--- 
Challenge solved with <3 by [Pedro Carara](http://linkedin.com/in/carara/)
