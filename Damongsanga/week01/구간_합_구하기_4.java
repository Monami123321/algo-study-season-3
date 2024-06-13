import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 구간_합_구하기_4 {


    public static void main(String[] args) throws IOException {

        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int sqrt = (int) Math.sqrt(N);
        st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[][] queries = new int[M][3];
        int[] result = new int[M];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            queries[i][0] = Integer.parseInt(st.nextToken())-1;
            queries[i][1] = Integer.parseInt(st.nextToken())-1;
            queries[i][2] = i;
        }

        // sqrt 구간 기준으로 정렬
        Arrays.sort(queries, (o1, o2) -> {
            if (o1[0]/sqrt == o2[0]/sqrt) return o1[1] - o2[1];
            return o1[0]/sqrt - o2[0]/sqrt;
        });

        // init
        int[] query = queries[0];
        int sum = 0;
        for (int i = query[0]; i <= query[1]; i++) {
            sum += arr[i];
        }
        result[query[2]] = sum;

        // 나머지는 구간 기준으로 정렬
        for (int i = 1; i < M; i++) {
            query = queries[i];
            int l = queries[i-1][0];
            int r = queries[i-1][1];
            while(l > query[0]) sum += arr[--l];
            while(l < query[0]) sum -= arr[l++];
            while(r > query[1]) sum -= arr[r--];
            while(r < query[1]) sum += arr[++r];
            result[query[2]] = sum;
        }

        // 출력
        StringBuilder sb = new StringBuilder();
        for (int res : result) {
            sb.append(res).append("\n");
        }
        System.out.println(sb);


    }

}