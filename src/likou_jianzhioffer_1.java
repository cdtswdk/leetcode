/**
 * @Auther: chendongtao
 * @Date: 2021/4/17 11:29
 * @Description:
 */
public class likou_jianzhioffer_1 {
    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(4);
        node1.next = node2;
        node2.next = node3;
        ListNode node4 = new ListNode(1);
        ListNode node5 = new ListNode(3);
        ListNode node6 = new ListNode(4);
        node4.next = node5;
        node5.next = node6;

        mergeTwoLists(node1, node4);
    }

    /**
     * 剑指 Offer 25. 合并两个排序的链表 递归
     *
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }

    //双指针迭代
    public static ListNode mergeTwoLists1(ListNode l1, ListNode l2) {
        ListNode dum = new ListNode(0), cur = dum;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        cur.next = l1 != null ? l1 : l2;
        return dum.next;
    }

    /**
     * 剑指 Offer 26. 树的子结构
     * @param a
     * @param b
     * @return
     */
    public boolean isSubStructure(TreeNode a, TreeNode b) {
        if(b == null){
            return false;
        }
        if(a.val == b.val){
            return isSubStructure(a.left,b.left) && isSubStructure(a.right,b.right);
        }else {
            return isSubStructure(a.left,b) || isSubStructure(a.right,b);
        }
    }
}
