package com.snail.sentinel.sdk;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;

import javax.sound.midi.Soundbank;
import java.util.ArrayList;

/**
 * @Auther: limin
 * @Date: 2019-08-10 21:00
 * @Description:
 */
public class SentinelDemo {

    public static void main(String[] args) {
        initFlowRule();
        while (true){
            Entry entry = null;
            try {
                entry = SphU.entry("HelloWorld");
                System.out.println("HelloWorld");
            } catch (BlockException e) {
                e.printStackTrace();
            }finally {
                if(entry!=null){
                    entry.exit();
                }
            }
        }
    }

    public static void initFlowRule(){

        ArrayList<FlowRule> flowRules = new ArrayList<>();
        FlowRule rule = new FlowRule();
        rule.setResource("HelloWorld");
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        // Set limit QPS to 20.
        rule.setCount(20);
        flowRules.add(rule);
        FlowRuleManager.loadRules(flowRules);
    }

}
