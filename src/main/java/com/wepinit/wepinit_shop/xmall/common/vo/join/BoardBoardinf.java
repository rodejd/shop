package com.wepinit.wepinit_shop.xmall.common.vo.join;

public class BoardBoardinf {

		private int sno;
		private String id;
		private String idx;
		private int num;
		public int getSno() {
			return sno;
		}
		public void setSno(int sno) {
			this.sno = sno;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getIdx() {
			return idx;
		}
		public void setIdx(String idx) {
			this.idx = idx;
		}
		public int getNum() {
			return num;
		}
		public void setNum(int num) {
			this.num = num;
		}
		@Override
		public String toString() {
			return "BoardBoardinf [sno=" + sno + ", id=" + id + ", idx=" + idx
					+ ", num=" + num + "]";
		}
		
}
