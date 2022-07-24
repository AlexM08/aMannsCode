public class Backpack {
        private int Gold;

        public Backpack() {
            Gold = 0;
        }

        public int getGold() {
            return Gold;
        }

        public void addGold(int Gold) {
            this.Gold += Gold;
        }

        public void removeGold(int Gold) {
            this.Gold -= Gold;
        }


        public void setGold(int Gold) {
            this.Gold = Gold;
        }
}
