# 使用技術

- SpringBoot
- Mybatis-Plus
- thymeleaf
- BootStrap

開啟項目前需修改 application.yml 中的上傳文件路徑

# 功能展示

## 首頁
**簡單的分頁功能可上下頁**
![Snipaste_2021-07-22_15-50-42](https://user-images.githubusercontent.com/86906814/126605891-b803505c-838b-48d7-bb47-a6f8010006a2.jpg)

**搜尋功能，可用書名搜尋想要的書籍**
![Snipaste_2021-07-22_15-52-22](https://user-images.githubusercontent.com/86906814/126606048-3d502653-789e-4079-9833-a87b9dd2d143.jpg)

**點擊左側分類，展示不同分類的書籍**
![Snipaste_2021-07-22_15-53-54](https://user-images.githubusercontent.com/86906814/126606297-58a106ac-bc56-4803-994b-f75674310f9f.jpg)




## 用戶

**當用戶註冊的帳號存在時，或登入密碼輸入錯誤時給予提示。**
![Snipaste_2021-07-22_15-09-34](https://user-images.githubusercontent.com/86906814/126601730-c60ff514-f900-4b25-b053-72ee014b7132.jpg)
![Snipaste_2021-07-22_15-18-50](https://user-images.githubusercontent.com/86906814/126602645-e7b3f32f-359a-4f56-9256-404d7fe841c9.jpg)


### 編輯個人資料
![Snipaste_2021-07-22_15-21-21](https://user-images.githubusercontent.com/86906814/126602835-9a68fa48-94be-4db1-9e0f-3ed36d8258bb.jpg)

### 修改密碼
![Snipaste_2021-07-22_15-24-24](https://user-images.githubusercontent.com/86906814/126603485-a095154f-3d31-4fd5-a6c5-f55f82b85aec.jpg)
**根據用戶修改的成功與否給予相對應的提示**
![Snipaste_2021-07-22_15-25-30](https://user-images.githubusercontent.com/86906814/126603499-b19c1287-757a-4184-840e-1eec8dc1e941.jpg)
![Snipaste_2021-07-22_15-26-16](https://user-images.githubusercontent.com/86906814/126603507-c0f109ae-156c-4130-9354-d903d583d74c.jpg)
![Snipaste_2021-07-22_15-26-41](https://user-images.githubusercontent.com/86906814/126603514-c693e47f-3e0f-40f4-8232-7b3759c7508b.jpg)


### 查看購買的訂單內容
![Snipaste_2021-07-22_15-30-36](https://user-images.githubusercontent.com/86906814/126604323-2173b2ba-fdf3-4513-b998-b03e55ab3120.jpg)

**點進去可看到購買的圖書資料**
![Snipaste_2021-07-22_15-30-49](https://user-images.githubusercontent.com/86906814/126604333-612c0f79-2956-47cc-a31f-1fbb8240c7aa.jpg)

## 購物車
### 添加商品到購物車
**點擊按鈕將圖書將入購物車後，會跳出提示框提示用戶**
![Snipaste_2021-07-22_15-59-22](https://user-images.githubusercontent.com/86906814/126606907-ead22d02-c5f1-4185-9523-3609a6d82c3d.jpg)

**圖書庫存不足時，添加的按鈕無法點擊**
![Snipaste_2021-07-22_15-58-14](https://user-images.githubusercontent.com/86906814/126607009-5ecbe3fb-0c70-4548-9843-787bc0a3d65a.jpg)

## 購物車內功能
### 修改數量
**點擊畫面中的 + 或 - 按鈕，或是修改框框內的數字，可修改圖書的數量，並即時更新頁面中顯示的商品總數、總金額，數據庫中的數據也會同時修改。**
![Snipaste_2021-07-22_16-04-23](https://user-images.githubusercontent.com/86906814/126607530-65849a61-e0ad-4b95-82cb-1a453b4667c0.jpg)

### 清空購物車
**會跳出提示框詢問用戶是否要清空**
![Snipaste_2021-07-22_16-11-45](https://user-images.githubusercontent.com/86906814/126608382-1a46b469-458e-43e9-840f-ad2fa57c74a0.jpg)

## 確認訂單頁面
**可查看或編輯收貨人資料，以及再次確認購買的商品內容**
![Snipaste_2021-07-22_16-14-56](https://user-images.githubusercontent.com/86906814/126608785-d890d0ad-8a2f-45f9-b67a-6619a3f5082f.jpg)

**修改收貨人資料**
![Snipaste_2021-07-22_16-17-47](https://user-images.githubusercontent.com/86906814/126609077-eecf809c-c4d1-4570-815f-db0705062b9b.jpg)

## 訂單完成
![Snipaste_2021-07-22_16-22-33](https://user-images.githubusercontent.com/86906814/126609746-3acfa3a0-a01b-4b22-9dfb-ddfddba00f33.jpg)


## 後台管理

### 管理圖書類型
![Snipaste_2021-07-22_16-29-50](https://user-images.githubusercontent.com/86906814/126610877-a1b855be-9a4b-4abc-b50e-ca37ac70eae9.jpg)


#### 新增
![Snipaste_2021-07-22_16-30-07](https://user-images.githubusercontent.com/86906814/126610921-bdce74f1-cedc-4ae7-a13c-e919ddf0ad20.jpg)

**成功後首頁的左側會顯示新增的類型**
![Snipaste_2021-07-22_16-35-50](https://user-images.githubusercontent.com/86906814/126611302-b5e00b67-5649-445d-b180-7df506730c07.jpg)


#### 修改
![Snipaste_2021-07-22_16-30-26](https://user-images.githubusercontent.com/86906814/126610970-7a2b69ab-6480-4772-beb3-096329d04351.jpg)

#### 刪除
![Snipaste_2021-07-22_16-30-38](https://user-images.githubusercontent.com/86906814/126611113-13e9bfc2-0afe-4d81-8736-ec2e1fd31cda.jpg)

  
### 圖書管理
![Snipaste_2021-07-22_16-43-07](https://user-images.githubusercontent.com/86906814/126612091-88d09c50-87c7-4b45-abe7-60a500c014ce.jpg)

#### 新增
![Snipaste_2021-07-22_16-41-03](https://user-images.githubusercontent.com/86906814/126612111-85ea61a0-cde6-4e08-8e67-5d59fc747561.jpg)

#### 修改
![Snipaste_2021-07-22_16-41-35](https://user-images.githubusercontent.com/86906814/126612128-990b9cc1-fcfd-4993-91cd-684d10746d57.jpg)

#### 刪除
![Snipaste_2021-07-22_16-41-53](https://user-images.githubusercontent.com/86906814/126612144-269e5fc6-dc4a-435f-b2dc-67ec9840395e.jpg)


## 訂單管理
![Snipaste_2021-07-22_16-46-56](https://user-images.githubusercontent.com/86906814/126612567-f7135b1c-7474-4843-9460-8d83f22bda6e.jpg)

### 變更訂單狀態
![Snipaste_2021-07-22_16-48-02](https://user-images.githubusercontent.com/86906814/126612651-a6c99246-e3f3-4c68-89b7-69cce918f7c1.jpg)



