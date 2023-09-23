package SD.Observer;

import java.util.*;

public class CurrentConditionDisplay implements Observer, DisplayElement {

    private float temp;
    private float humidity;
    private WeatherData weatherData;

    public CurrentConditionDisplay(WeatherData weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    @Override
    public void display() {
        System.out.println("CurrentConditionDisplay: temp: " + this.temp + " and humidity: " + this.humidity);
    }

    @Override
    public void update() {
        this.temp = weatherData.getTemp();
        this.humidity = weatherData.getHumidity();
        display();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.largestVariance("aababbb");
    }

    static class Solution {
        public int largestVariance(String s) {
            int n = s.length();
            int[][] dp = new int[n+1][n+1];
            for (int i = 0; i < n + 1; i++) Arrays.fill(dp[i], -1);
//            return rec(0, -1, s.length(), s, dp);
            return dpSol(n, s);
        }

        private int dpSol(int n, String s) {
            int[][] dp = new int[n+1][n+1];

            for (int i = n-1; i >= 0; i--) {
                for (int j = i-1; j >= -1; j--) {
                    int len = Math.max(partition(s, j, i), dp[i+1][i]);
                    if (j != -1) len = Math.max(len, dp[i+1][j]);
                    dp[i][j+1] = len;
                }
            }
            return dp[0][0];
        }

        private int rec(int i, int prev, int n, String s, int[][] dp) {
            if (i == n) {
                return 0;
            }
            if (dp[i][prev+1] != -1) return dp[i][prev+1];
            int len = Math.max(partition(s, prev, i), rec(i+1, i, n, s, dp));
            return dp[i][prev+1] = Math.max(len, rec(i+1, prev, n, s, dp));
        }

        private int partition(String s, int prev, int i) {
            String subStr = s.substring(prev+1, i+1);
            Map<Character, Integer> m = new HashMap<>();
            for (char c : s.toCharArray()) {
                m.put(c, m.getOrDefault(c, 0)+1);
            }
            int mn = Integer.MAX_VALUE;
            int mx = 0;
            for (char c : m.keySet()) {
                mn = Math.min(mn, m.get(c));
                mx = Math.max(mx, m.get(c));
            }
            return mx-mn;
        }
    }
}
