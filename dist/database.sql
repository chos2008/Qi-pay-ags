ALTER TABLE `pay_transaction_notify_endpoint`     
    ADD COLUMN `forward_destination` VARCHAR(255) NULL AFTER `notify_endpoint`,    
    CHANGE `notify_endpoint` `notify_endpoint` VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL ;