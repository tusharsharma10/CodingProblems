package leetcode;

public class Easy {

    public static int[] shuffle(int[] nums, int n) {

        int ans[] = new int[nums.length];

        int j = 0;
        int k = n + 1;
        for (int i = 0; i < nums.length; i++) {

            if (i % 2 == 0) {
                ans[i] = nums[j];
                j++;
            } else {
                ans[i] = nums[k];
                k++;
            }

        }

        return ans;
    }

/**
 * https://leetcode.com/problems/running-sum-of-1d-array/
 * @param args
 */

public int[] runningSum(int[] nums) {
        
    int sum[] = new int[nums.length];
    int temp = 0;
    for(int i = 0;i<sum.length;i++){

        temp += nums[i];
        sum[i] = temp;
    }
    return sum;
}

    public static void main(String[] args) {

    }
}
