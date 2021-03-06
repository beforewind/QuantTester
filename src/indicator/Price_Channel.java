package indicator;

public class Price_Channel implements IIndicator {

	private final int InpChannelPeriod;

	public Price_Channel(int ChannelPeriod) {
		this.InpChannelPeriod = ChannelPeriod;
	}

	public Price_Channel() {
		this(22);
	}

	private float[] ExtHighBuffer = null;
	private float[] ExtLowBuffer = null;
	private float[] ExtMiddBuffer = null;

	@Override
	public void calculate(float[] open, float[] high, float[] low, float[] close) {
		int rates_total = close.length;

		ExtHighBuffer = new float[rates_total];
		ExtLowBuffer = new float[rates_total];
		ExtMiddBuffer = new float[rates_total];

		int prev_calculated = 0;
		int i, limit;
		// --- check for rates
		if (rates_total < InpChannelPeriod)
			return;
		// --- preliminary calculations
		if (prev_calculated == 0)
			limit = InpChannelPeriod;
		else
			limit = prev_calculated - 1;
		// --- the main loop of calculations
		for (i = limit; i < rates_total; i++) {
			ExtHighBuffer[i] = Highest(high, InpChannelPeriod, i);
			ExtLowBuffer[i] = Lowest(low, InpChannelPeriod, i);
			ExtMiddBuffer[i] = (ExtHighBuffer[i] + ExtLowBuffer[i]) / 2.0f;
		}
	}

	// +------------------------------------------------------------------+
	// | get highest value for range |
	// +------------------------------------------------------------------+
	float Highest(float[] array, int range, int fromIndex) {
		float res;
		int i;
		// ---
		res = array[fromIndex];
		for (i = fromIndex; i > fromIndex - range && i >= 0; i--) {
			if (res < array[i])
				res = array[i];
		}
		// ---
		return (res);
	}

	// +------------------------------------------------------------------+
	// | get lowest value for range |
	// +------------------------------------------------------------------+
	float Lowest(float[] array, int range, int fromIndex) {
		float res;
		int i;
		// ---
		res = array[fromIndex];
		for (i = fromIndex; i > fromIndex - range && i >= 0; i--) {
			if (res > array[i])
				res = array[i];
		}
		// ---
		return (res);
	}

	@Override
	public float[] getBufferById(int id) {
		switch (id) {
		case 0:
			return ExtHighBuffer;
		case 1:
			return ExtLowBuffer;
		case 2:
			return ExtMiddBuffer;
		default:
			return null;
		}
	}
}
