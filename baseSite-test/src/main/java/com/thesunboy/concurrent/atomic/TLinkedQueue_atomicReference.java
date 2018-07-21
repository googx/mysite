/**
 * 代码为通用实现,用作它途,并以此牟利的不合法行为,盖于本开发者(thesunboy.com)无关
 */
package com.thesunboy.concurrent.atomic;

import java.util.concurrent.atomic.AtomicReference;

import com.thesunboy.commons.config.MyLogFactory;

import com.thesunboy.utils.mylog.core.MyLogger;
import com.thesunboy.utils.mylog.core.MyLogger.LogImplEnum;

/**
 * <b>功能说明:</b><p>
 *一些说明写这里
 * </p></br> <b>设计思想、目的:</b><p> 
 *一些说明写这里
 * </p></br><b>设计缺陷: </b><p> 一些说明写这里
</p>
 * @author hx940929
 * @CreateDate 2017-8-11 - 上午11:11:50 
 * @Encoding UTF-8
 * @Version 1.0
 * @WebSite <a href="https://www.thesunboy.com">访问开发者个人主页</a>
 * @QQ 836845967
 */
public class TLinkedQueue_atomicReference<E>
{
	private final MyLogger logger = MyLogFactory.getLogger(TLinkedQueue_atomicReference.class, LogImplEnum.ConsoleLogImpl);



	// 空节点,初始原子对象应该要指向这个节点. 类似于加锁协议中的 类的私有对象锁//在并发书上P272 这个对象叫哑结点, 在标准数据结构上
	// 都是这样称呼的
	private final Node<E> dummyNode = new Node<E>(null, null);

	private final AtomicReference<Node<E>> head = new AtomicReference<>(dummyNode);// 头部
	private final AtomicReference<Node<E>> tail = new AtomicReference<>(dummyNode); // 尾部

	/**
	 * 
	 * 简要功能说明:
	 * <p>
	 * 使用cas无锁算法向队列中插入元素,线程安全,
	 * </p>
	 * <b>注意事项: </b>
	 * 
	 * </br>
	 * 
	 * @Author hx940929
	 * @Date 2017-8-11 - 上午11:46:56
	 * @param item
	 * @return 不会返回flase, 插入操作失败时,会不断自旋等待尝试插入.
	 */
	public boolean put(E item)
	{
		Node<E> newNode = new Node<E>(item, null);
		int repyCount = 0;// 该线程累计竞争失败,重试次数
		while (true)// 当该线程cas竞争失败时,使用重试
		{
			Node<E> curTailNode = tail.get();// 先获取当前linked队列中最末尾的元素
			Node<E> tailNextNode = curTailNode.getNextNode().get();// 由于多线程原因,可能这个时候有其他线程抢先在tail插入了一个新元素了,
																   // 所以当前获取的这个最后一个元素下面又有了新的元素了,状态不一致并不是队列的tail了,所以下面要判断下这个是否是null,是null这说明队列处于稳定状态,非null是中间状态
			if(curTailNode == tail.get())
			{
				if(tailNextNode != null)
				{
					// 队列处于中间状态,推进尾节点
					tail.compareAndSet(curTailNode, tailNextNode);
				}
				else
				{
					// 队列处于稳定状态,尝试插入新节点
					if(curTailNode.nextNode.compareAndSet(null, newNode))
					{
						// 插入操作成功,尝试推进尾节点
						tail.compareAndSet(curTailNode, newNode);
						return true;
					}
				}
			}
			repyCount++;
		}
	}


private static class Node<E>
{
	final E item;
	final AtomicReference<Node<E>> nextNode;

	public Node(E item, Node<E> nextNode)
	{
		this.item = item;
		this.nextNode = new AtomicReference<Node<E>>(nextNode);
	}

	/**
	 * @return the item
	 */
	public E getItem()
	{
		return item;
	}

	/**
	 * @return the nextNode
	 */
	public AtomicReference<Node<E>> getNextNode()
	{
		return nextNode;
	}

}

}
