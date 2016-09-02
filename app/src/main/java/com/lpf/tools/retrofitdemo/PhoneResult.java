package com.lpf.tools.retrofitdemo;

import java.util.List;

/**
 * liupf1213@gmail.com
 * Created by liupf5 on 16/9/2.
 * Description:
 */
public class PhoneResult {


    /**
     * celltype : loopimage
     * title :
     * more :
     * sort : 1
     * datalist : [{"linkurl":"http://m.lenovo.com.cn/android/activity/marketing/air12cto/index.html","picurl":"http://p3.lefile.cn/product/adminweb/2016/09/01/65e4d854-b902-4ebf-a453-b0538a79ead3.jpg","price":"","name":"","name2":"","itemcode":"","index":"loopimagecbadd917-d992-4955-b7b1-6d3943e59f751","sort":1},{"linkurl":"http://m.lenovo.com.cn/android/activity/marketing/qmkxj/index.html","picurl":"http://p2.lefile.cn/product/adminweb/2016/08/26/5ed167b3-2fe8-405a-a841-3f35429fa4c5.jpg","price":"","name":"","name2":"","itemcode":"","index":"loopimagecbadd917-d992-4955-b7b1-6d3943e59f752","sort":2},{"linkurl":"http://m.lenovo.com.cn/android /activity/marketing/game/index.html","picurl":"http://p3.lefile.cn/product/adminweb/2016/09/01/60a67a49-3205-4110-a53e-7f8a177efe33.jpg","price":"","name":"","name2":"","itemcode":"","index":"loopimagecbadd917-d992-4955-b7b1-6d3943e59f756","sort":3},{"linkurl":"http://m.lenovo.com.cn/android/activity/marketing/kgb/index.html","picurl":"http://p3.lefile.cn/product/adminweb/2016/08/25/3cab5807-f546-49a1-b89f-55a3eb1742f7.jpg","price":"","name":"","name2":"","itemcode":"","index":"loopimagecbadd917-d992-4955-b7b1-6d3943e59f7518","sort":4},{"linkurl":"http://m.lenovo.com.cn/android/activity/schoolserviceapp/index.html","picurl":"http://p1.lefile.cn/product/adminweb/2016/09/01/ddeeb01a-cc99-4b1f-a4ba-56bb0fc8bd9d.jpg","price":"","name":"","name2":"","itemcode":"","index":"loopimagecbadd917-d992-4955-b7b1-6d3943e59f7520","sort":5}]
     */

    private List<DataBean> Data;

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean {
        private String celltype;
        private String title;
        private String more;
        private int sort;
        /**
         * linkurl : http://m.lenovo.com.cn/android/activity/marketing/air12cto/index.html
         * picurl : http://p3.lefile.cn/product/adminweb/2016/09/01/65e4d854-b902-4ebf-a453-b0538a79ead3.jpg
         * price :
         * name :
         * name2 :
         * itemcode :
         * index : loopimagecbadd917-d992-4955-b7b1-6d3943e59f751
         * sort : 1
         */

        private List<DatalistBean> datalist;

        public String getCelltype() {
            return celltype;
        }

        public void setCelltype(String celltype) {
            this.celltype = celltype;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getMore() {
            return more;
        }

        public void setMore(String more) {
            this.more = more;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public List<DatalistBean> getDatalist() {
            return datalist;
        }

        public void setDatalist(List<DatalistBean> datalist) {
            this.datalist = datalist;
        }

        public static class DatalistBean {
            private String linkurl;
            private String picurl;
            private String price;
            private String name;
            private String name2;
            private String itemcode;
            private String index;
            private int sort;

            public String getLinkurl() {
                return linkurl;
            }

            public void setLinkurl(String linkurl) {
                this.linkurl = linkurl;
            }

            public String getPicurl() {
                return picurl;
            }

            public void setPicurl(String picurl) {
                this.picurl = picurl;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getName2() {
                return name2;
            }

            public void setName2(String name2) {
                this.name2 = name2;
            }

            public String getItemcode() {
                return itemcode;
            }

            public void setItemcode(String itemcode) {
                this.itemcode = itemcode;
            }

            public String getIndex() {
                return index;
            }

            public void setIndex(String index) {
                this.index = index;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }
        }
    }
}
