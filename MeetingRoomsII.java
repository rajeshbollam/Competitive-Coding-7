//The idea here is to assign a new room for a meeting if it doesn't fit in the existing rooms. 
//As we keep iterating through the list, we check if the current meeting could be scheduled to an already existing room.
//For this, to check the room with latest availability, we maintian a priority queue(min heap) which has all the end times of the existing meeting.
//If the current meeting fits in the top most room, we do not put the meeting in a new room, and we replace the top value by end time of the currently added meeting
//Time Complexity: O(nlogn) where n is the number of meetings
//Space Complexity: O(n) for heap                  
import java.util.List;
import java.util.PriorityQueue;

class Interval {
    public int start, end;
    public Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
}


 class Solution {
    public int minMeetingRooms(List<Interval> intervals) {
        //Collections.sort(intervals, ((a, b) -> a.get(1) - b.get(1)));
        if(intervals.size() == 0) return 0;
        intervals.sort((a, b) -> a.start - b.start);
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(intervals.get(0).end);
        int rooms = 1;
        for(int i = 1; i<intervals.size(); i++){
            if(intervals.get(i).start >= pq.peek()){
                pq.poll();
                pq.add(intervals.get(i).end);
            } else {
                pq.add(intervals.get(i).end);
                rooms++;
            }
        }
        return rooms;
    }
}
