package cs_test;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

// finisher(seq.aggregate(collector.supplier())(collector.accumulator(), collector.combiner()))
// T, A, R   - A = builder<T>
public class BasketCollector implements Collector <Map.Entry<String, Long>,  BasketBuilder, BasketSummary> {

	public static BasketCollector toCustomCollector() { return new BasketCollector() ; }
	
	@Override
	// A
	public Supplier<BasketBuilder> supplier() {
		return BasketBuilder::new ;
	}

	@Override
	// A, T    - accepts accumulator and the next element
	public BiConsumer <BasketBuilder, Map.Entry<String, Long>> accumulator() {
		return (bb, me) -> bb.accumulate(me.getKey(), me.getValue() ) ;
	}

	@Override
	// A  (A, A) -> A
	public BinaryOperator <BasketBuilder> combiner() {
		return (bb1, bb2) -> bb1.called("combiner", bb2) ;
	}

	@Override
	// A, R
	public Function<BasketBuilder, BasketSummary> finisher() {
		return BasketSummary::new ;
	}

	@Override
	public Set characteristics() {
		Set result = new HashSet() ;
		result.add(Collector.Characteristics.IDENTITY_FINISH) ;
		return result ;
	}

}
