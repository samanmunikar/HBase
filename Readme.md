Overview:

Download amazon video game review data from the link https://s3.amazonaws.com/amazon-reviews-pds/tsv/amazon_reviews_us_Video_Games_v1_00.tsv.gz . After extracting you will get a “.tsv” file where the columns are ‘tab’ separated. The description of the columns can be found in the link https://s3.amazonaws.com/amazon-reviews-pds/tsv/index.txt . For this assignment, you must run different queries on HBase using this dataset.

Point Distribution:

Part 1:

* Download and Insert this dataset to a HBase table named "GameTable":

* Keep three column familys:
(i) Info (contains columns ‘marketplace’ and ‘verified_purchase’)
(ii) Rating (contains columns 'star_rating', 'helpful_votes', 'total_votes', )
(iii) Review (contains columns 'review_headline', 'review_body')

* The columns under 'Rating' should be integer. The columns under 'Info' and 'Review' should be string.

* Concat 'customer_id' and 'review_id' by an underscore to use it as the Row Key. (for example, some random Row Key for a certain row can be 11_22, where 11 is the customer_id and 22 is the reiview_id.

* After creating the table and inserting data, run 'describe' command to show that the table has been created perfectly and then run 'scan' & 'limit' command to show 5 rows.

 

Part 2:

* Alter 'Review' column family to support 3 versions. Now, take a random row to put additional 2 different 'review_body'. Then show the all 3 'review_body' for this row.

* Find the 'review_bodys' that have the word 'awesome'.

* Find the 'review_headlines' that have any characters apart form alphanumerical characters (use regex).

Part 3:

* Find how many reviews have 'star_rating' equal to 5.

* Find the average 'helpful_vote' in the dataset. 

* Show the 'review_headlines' that got 1 'star_rating'.
