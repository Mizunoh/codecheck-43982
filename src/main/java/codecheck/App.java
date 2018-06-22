package codecheck;


public class App {
	public static void main(String[] args) {
		String[] param = args[0].split(" ", 0);
		int cnt = Integer.parseInt(param[0]); // カードの枚数
		int m = Integer.parseInt(param[1]); // プレイヤーのMP

		int[] attack = new int[cnt]; // カードの攻撃力用配列
		int[] cost = new int[cnt]; // カードのコスト用配列

		// カード情報
		for (int i = 0; i < cnt; i++) {
			attack[i] = Integer.parseInt(param[i + 2]); // カードの攻撃力
			cost[i] = Integer.parseInt(param[i + 3]); // カードのコスト
		}


		// 攻撃力の昇順に並べる
		for (int j = 0; j < cnt-1; j++) {
			int pos = j;
			for (int k = j+1; k < cnt; k++){
				if (attack[k] < attack[pos]){
					pos = k;
				}
			int w = attack[j];
			attack[j] = attack[pos];
			attack[pos] = w;
			int x = cost[j];
			cost[j] = cost[pos];
			cost[pos] = x;
			}
		}

		// 攻撃力が同じ場合、コスト順に並び替える
		for (int j = 0; j < cnt-1; j++) {
			int pos = j;
			for (int k = j+1; k < cnt; k++){
				if (attack[k] == attack[pos]){
					if (cost[k] < cost[pos]){
						pos = k;
						int w = attack[j];
						attack[j] = attack[pos];
						attack[pos] = w;
						int x = cost[j];
						cost[j] = cost[pos];
						cost[pos] = x;
					}
				}
			}
		}

		// 順番にコストをMPの範囲内で加算し、攻撃力の最大値を得る
		int mpCost = 0;
		int outputAttack = 0;
		for (int l = cnt; l <= 0; l--){
			if (mpCost + cost[l] <= m) {
				mpCost = mpCost + cost[l]; // コストを加算
				outputAttack = outputAttack + attack[l]; //攻撃力を加算
				System.out.println(outputAttack);
				if (mpCost == m){
					break;
				}
			}
		}
		System.out.println(outputAttack);
	}
}
