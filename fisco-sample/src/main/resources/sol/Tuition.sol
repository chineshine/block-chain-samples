pragma solidity ^0.4.24;

import "./Table.sol";

contract Tuition {

    // 构造体
    constructor() public {
        createTable();
    }

    function createTable() private {
        TableFactory tf = TableFactory(0x1001);
        tf.createTable("cs_tuition", "username", "fee_value");
    }

    // 打开表
    function openTable() private returns (Table) {
        TableFactory tf = TableFactory(0x1001);
        Table table = tf.openTable("cs_tuition");
        return table;
    }

    // 查询学生缴纳情况
    function select(string username) public constant returns (int256, uint256){
        Table table = openTable();
        Entries entries = table.select(username, table.newCondition());
        uint256 feeValue = 0;
        if (0 == uint256(entries.size())) {
            return (- 1, feeValue);
        } else {
            Entry entry = entries.get(0);
            return (0, uint256(entry.getInt("fee_value")));
        }
    }

    // 缴费
    function pay(string username, uint256 fee_value) public returns (int256) {
        int256 exist = 0;
        uint256 value = 0;
        (exist, value) = select(username);
        if (exist == 0) {
            //        插入失败, 用户存在
            return - 1;
        }
        Table table = openTable();
        Entry entry = table.newEntry();
        entry.set("username", username);
        entry.set("fee_value", fee_value);
        int count = table.insert(username, entry);
        if (count == 1) {
            return 0;
        }
        // 插入失败  其他错误
        return - 2;
    }

    // 补缴
    function appendfee(string username, uint256 fee_value) public returns (int256){
        int256 code = 0;
        uint256 value = 0;
        (code, value) = select(username);
        if (code != 0) {
            // 补缴失败, 用户不存在
            return - 1;
        }
        value = value + fee_value;
        Table table = openTable();
        Entry entry = table.newEntry();
        entry.set("username", username);
        entry.set("fee_value", value);
        int count = table.update(username, entry, table.newCondition());
        if (count == 1) {
            return 0;
        }
        // 其他错误, 补缴失败
        return - 2;
    }

    // 退费
    function refund(string username) public returns (int256, uint256) {
        int256 code = 0;
        uint256 value = 0;
        (code, value) = select(username);
        if (code != 0) {
            // 无此账户, 直接返回
            return (0, 0);
        }
        Table table = openTable();
        int count = table.remove(username, table.newCondition());
        if (count == 1) {
            return (0, value);
        }
        // 退费失败, 其他原因
        return (- 1, 0);
    }

}