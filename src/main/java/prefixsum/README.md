Background
Since existing solutions kind of quickly dive into pre sum and map.
I have some trouble understanding solution deduction.
After spening some time to identify my blockers, I would like to share how to intuitively diagnosis the problem pattern, detect sub problems and deduct equations.

Diagnosis
Step1. Detect problem pattern (Notice we only care about result of sub sums instead of each elements)
Observe that we actually only care about results of sub sum instead of value of individual elements.
Ex:
input =[1,2,3] k=5
input =[1,1,1,3] k=5
input =[1,-1,1,2,3] k=5

While we iterate though array and encounter value 3, all these examples will achieve sub sum=5=k.
Thus for value 3, it only need previous sum of 2 no matter what it composed. Ex: [2], [1,1], [-1,1,2]
->We notice that sub sum is going to help us.

Step2. Deduct equations (Notice how sub sums can help us)
Now we have concept of only caring about sub sum instead of individual elements and sub sum is going to help us, but how?

Let’s assume a situation of following
Ex:
input = [A,…,E,3,F…] k=5

Let’s check value 3 with front part of array first (How value 3 interact with previous elements).
[A,…,E,3]
As previously mentioned, we don’t care about how rest of previous elements composed.
Ex:
E+3= k
D+E+3=k
C+D+E+3=k.

(Base on the fact that we only need to care about whether C+D+E+3=k, or D+E+3=k)
How do we know we’ve identify there is sub sum =k?
Let’s assume (D+E+3=k)
sum =A+B+C+D+E+3
preSum = A+B+C

Thus, we can compose critical equation

sum - preSum = k
Thus we understand recording sub sums is reasonable and how it helps us.

Since we’re looking for specific preSum to compose value k
What specific preSum we're looking?
Some math operation results in

sum - k = preSum
This means we could record cumulative sums to locate current sum and preSums.
While we are looking for matching preSum to compose k, we could use 'sum-k’ as key to locate it.

Step3. Detect sub problem(Handle negative value and multiple matching counts)
From previous conclusion negative doesn’t matter since we recording sums.

For multiple matching counts
Ex: 
input [0,-1,1,2,3] k=5

We notice that it’s necessary to record occurrence of specific preSum.
In this case, we need to know preSum 2 occur three times. [0,-1,1,2], [-1,1,2], [2]
This indicate that it's necessary to record preSum counts
Map(preSum,  counts)

Step4. Sudo code (Create abstract algorithm)
Now we know we should record sub sum in this way interatively

map.put(sum, count+1)
Lookup preSum in this way

map.get(sum - k)
Step5. Formal algorithm and Implementation
There are already great detail of solution implementations here.
https://leetcode.com/problems/subarray-sum-equals-k/discuss/102106/Java-Solution-PreSum-%2B-HashMap
This implementation will result in
Time complexity : O(n). The array is traversed only once.
Space complexity : O(n). Hashmap can contain up to n distinct entries in the worst case.