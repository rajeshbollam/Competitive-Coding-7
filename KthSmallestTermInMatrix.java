//The approach here is to use binary search to find mid element of the matrix and if the kth smallest element lies from low to mid, if it does, we move high to mid - 1, else, we move low to mid + 1
//To see if the kth smalles element lies in the low to mid range, we will calculate the count of number of elements less than or equal to k in the range.
//We terminate if low>high and low pointer points to the kth smallest element
//For finding the count, we start from bottom left index and we check if the element is less than or equal to the mid, if yes, we add to the count the column value and move right, if not, we move top and continue 
//Time Complexity: O(nlog(max-min))//where n is the length of the matrix and max and min are maximum and minimum values in the matrix
//Space Complexity: O(1)
class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        int low = matrix[0][0];
        int high = matrix[n-1][n-1];
        while(low<=high){
            int mid = low + (high - low)/2;
            int cnt = countLessOrEqual(matrix, mid);
            if(cnt >=k){
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    private int countLessOrEqual(int[][] matrix, int num){
        int n = matrix.length;
        int row = n-1; int col = 0;
        int cnt = 0;
        while(row >=0 && col < n){
            if(matrix[row][col] <= num){ //go right if the current number is less than or equal to num
                cnt = cnt + row + 1;
                col = col + 1;
            } else {
                row = row - 1;
            }
        }
        return cnt;
    }
}