# Problema 1
![[Pasted image 20250710140538.png|center|550]]

Le forze agenti sul punto sono le seguenti

![[Pasted image 20250710141139.png|center|500]]

## a.) Calcolare modulo forza F
Fissando il sistema cartesiano come in figura risulta
$$\begin{align}&N_{x}=0\qquad N_{y}=|\:\overrightarrow{N}\:|=N\qquad (m\overrightarrow{g})_{x}=-mg\sin\Theta\qquad (m\overrightarrow{g})_{y}=-mg\cos\Theta\\
&F_{dx}=-\mu_{d}\:N\qquad F_{dy}=0\qquad F_{x}=|\:\overrightarrow{F}\:|=F\qquad F_{y}=0\end{align}$$

Dato che il punto si muove con velocità costante, la risultante delle forze agenti sul punto materiale è nulla, quindi $$\overrightarrow{F}+m\overrightarrow{g}+\overrightarrow{F_{d}}+\overrightarrow{N}=0$$
da cui 
$$\begin{cases} F_{x}+(m\overrightarrow{g})_{x}+F_{dx}+N_{x}=0\\ \\
F_{y}+(m\overrightarrow{g})_{y}+F_{dy}+N_{y}=0
\end{cases}\implies
\begin{cases} F-mg\sin\Theta-\mu_{d}N=0\\ \\
-mg\cos\Theta+N=0
\end{cases}$$
ricaviamo così
$$\begin{align}\begin{cases}F=mg\sin\Theta+\mu_dN \\
N=mg\cos\Theta\end{cases}\implies F&=mg\sin\Theta+\mu_d(mg\cos\Theta)=\\
&=mg\sin\Theta+mg\cos\Theta\mu_d=\\
&=mg(\sin\Theta+\cos\Theta\mu_d)
\end{align}$$

Sostituendo con i valori dati otteniamo 
$$F=(10kg)\cdot(9.81 \frac{m}{s^{2}})\cdot(\sin30°+0.1\cos30°)\approx57,55N$$
## b.) Calcolare i lavori svolti dalla forza peso, forza fune e forza attrito

L'energia cinetica del punto non varia durante il moto dato che il modulo della velocità istantanea è costante. Per il teorema dell'energia cinetica, il lavoro svolto complessivamente dalle forze agenti sul punto durante il moto è nullo. Dato che la reazione normale $\overrightarrow{N}$ del piano inclinato non compie lavoro, allora deve risultare $$W_{p}+W_{f}+W_{d}=0$$
Abbiamo $$W_{p}=mg(h_{i}-h_{f})$$ dove $h_{i}$ e $h_{f}$ sono la quota verticale iniziale e finale del punto. Poiché tra l'istante iniziale e finale il punto si sposta di un tratto $L$ in salita, risulta $$h_{i}-h_{f}=-L\sin\Theta$$ quindi $$W_{p}=-mgL\sin\Theta=-(10kg)\cdot(9.81 \frac{m}{s^{2}})\cdot(10m)\cdot(\sin30°)\approx-490,5J$$
La forza $\overrightarrow{F}$ è costante durante il moto del punto, per questo il lavoro svolto dalla forza $\overrightarrow{F}$ durante il tratto di lunghezza $L$ è positivo dato che il la forza agisce nella stessa direzione del movimento del punto $$W_{f}=FL=mg(\sin\Theta+\cos\Theta\mu_d)L\approx(57,55N)\cdot(10m)\approx575,5J$$
Dal teorema dell'energia cinetica ricaviamo il lavoro svolto dalla forza d'attrito dinamico $$\begin{align}W_{d}=-W_{p}-W_{f}&=mgL\sin\Theta-mg(\sin\Theta+\cos\Theta\mu_d)L=\\\\
&=\cancel{mgL\sin\Theta}\cancel{-mgL\sin\Theta}-mg\cos\Theta\mu_{d}L=\\\\
&=-0.1\cdot(10kg)\cdot(9.81 \frac{m}{s^{2}})\cdot(10m)\cdot\cos30°\approx-84.96J\end{align}$$
## c.) Calcolare $\Theta$ che massimizza il modulo della forza della corda

Dalla formula ottenuta nel punto a.) $$F=mg(\sin\Theta+\cos\Theta\mu_d)$$ possiamo determinare il valore di $\Theta$ per cui $F$ è massima derivando rispetto a $\Theta$ $$\frac{dF}{d\Theta}=\cancel{mg}(\cos\Theta-\sin\Theta\mu_d)=0$$ da cui $$\frac{\cos\Theta}{\sin\Theta}= \frac{\cancel{\sin\Theta}\mu_d}{\cancel{\sin\Theta}}\implies\tan\Theta= \frac{1}{\mu_{d}}\implies\Theta=\arctan(\frac{1}{\mu_{d}})$$
Da questo sappiamo che $F$ è crescente per $0°<\Theta<\arctan(\frac{1}{\mu_{d}})$ ed è decrescente per $\arctan(\frac{1}{\mu_{d}})<\Theta<90°$.
Otteniamo quindi un massimo per $F$ per $$\Theta=\Theta_{max}=\arctan(\frac{1}{\mu_{d}})=\arctan(\frac{1}{0.1})=\arctan(10)\approx84.29°\approx1,47rad$$
Dunque $$F_{max}(\Theta_{max})=mg(\sin\Theta_{max}+\mu_{d}\cos\Theta_{max})$$
Dalle relazioni tra le funzioni goniometriche ricaviamo $$\sin\Theta_{max}=\frac{\tan\Theta_{max}}{\sqrt{1+(\tan\Theta_{max})^{2}}}\qquad\cos\Theta_{max}=\frac{1}{\sqrt{1+(\tan\Theta_{max})^{2}}}$$
dalla relazione ottenuta precedentemente $\tan\Theta=\frac{1}{\mu_{d}}$ la formula per il calcolo di $F_{max}$ diventa $$\begin{align}F&=mg\bigg(\frac{\tan\Theta_{max}}{\sqrt{1+(\tan\Theta_{max})^{2}}}+\frac{\mu_{d}}{\sqrt{1+(\tan\Theta_{max})^{2}}}\bigg)=\\\\
&=mg\Bigg(\frac{\frac{1}{\mu_{d}}}{\sqrt{1+(\frac{1}{\mu_{d}})^{2}}}+\frac{\mu_{d}}{\sqrt{1+(\frac{1}{\mu_{d}})^{2}}}\Bigg)=\\\\
&=mg\frac{{\frac{1}{\mu_{d}}}+\mu_{d}}{\sqrt{\frac{1+\mu_{d}^{2}}{\mu_{d}^{2}}}}=mg\frac{\frac{1+\mu_{d}^{2}}{\mu_{d}}}{\sqrt{\frac{1+\mu_{d}^{2}}{\mu_{d}^{2}}}}=mg\cdot\frac{1+\mu_{d}^{2}}{\mu_{d}}\cdot\sqrt{\frac{\mu_{d}^{2}}{1+\mu_{d}^{2}}}=\\\\
&=mg\cdot\frac{1+\mu_{d}^{2}}{\cancel{\mu_{d}}}\cdot\frac{\cancel{\mu_{d}}}{\sqrt{1+\mu_{d}^{2}}}=mg\cdot\frac{(1+\mu_{d}^{2})^{1}}{(1+\mu_{d}^{2})^{\frac{1}{2}}}=mg\cdot(1+\mu_{d})^{\frac{1}{2}}=\\\\
&=(10kg)(9.81 \frac{m}{s^{2}})\cdot\sqrt{1+0.1^{2}}\approx98.59 N\end{align}$$
# Problema 2
![[Pasted image 20250711122732.png|center|550]]

![[Pasted image 20250712143711.png|center|500]]
## a.) Calcolo frequenza oscillazioni armoniche
Dopo il rilascio, il moto del punto è armonico. La pulsazione di un punto di massa $M$ collegato all'estremità di una molla con costante elastica $k$ e se il moto avviene lungo l'asse di allungamento è $$\omega=\sqrt{\frac{k}{M}}$$
essendo $\omega=2\pi f$ allora otteniamo $f$ nel seguente modo $$f=\frac{\omega}{2\pi}=\frac{1}{2\pi}\cdot\sqrt{\frac{k}{M}}=\frac{1}{2\pi}\sqrt{\frac{50 \frac{N}{m}}{0.1kg}}\approx3.56 Hz$$
## b.) Calcolo energia meccanica
Sapendo che il punto è inizialmente fermo e la molla allungata di un tratto $A$, l'energia meccanica coincide con l'energia potenziale elastica iniziale. $$E_{m}=\frac{1}{2}kA^{2}=\frac{1}{2}\bigg(50 \frac{N}{m}\bigg)(0.2 m)^{2}=1J$$
Durante il moto del corpo, l'unica forza che compie lavoro è la forza elastica. Per ipotesi è assente l'attrito dinamico e le altre forze agenti sul punto, ovvero la forza peso e la reazione normale del piano orizzontale, non compiono lavoro, perché ad ogni istante agiscono lungo la direzione perpendicolare alla direzione del moto. Dato che l'unica forza che compie lavoro è la forza elastica, l'energia meccanica $E_{m}$ si conserva durante il moto, in quanto la forza elastica è conservativa

## c.) Piano ruvido
Dato che il punto è fermo all'istante iniziale (da ipotesi) e all'istante finale, il lavoro totale svolto dalle forze agenti è nullo, in quanto l'energia cinetica non varia tra l'istante iniziale e finale. Le uniche forze che compiono lavoro sul punto sono la forza elastica della molla e la forza di attrito dinamico. $$W_{e}+W_{d}=0$$
Abbiamo che $$W_{e}=U_{el, i}- U_{el,f}$$
ma $U_{el,f}$ è nulla in quanto nella posizione finale la molla è in posizione di riposo. Risulta quindi $$W_{e}=U_{el,i}=\frac{1}{2}kA^{2}=1J$$
da cui otteniamo $W_{d}=-W_{e}=-1J$

# Problema 3

![[Pasted image 20250712150301.png|center|500]]

## a.) Calcolo di $B_{0}$ 
![[Pasted image 20250712150821.png|center|300]]


Per il teorema di Ampere sappiamo che $$2\pi dB_{0}=\mu_0i_{0}$$
ricaviamo quindi $$B_{0}=\frac{\mu_{0}i_{0}}{2\pi d}=\frac{\bigg(4\cancel\pi\cdot 10^{-7}\frac{T\cdot m}{A}\bigg)\cdot 1A}{2\cancel\pi\cdot0.5m}=4\cdot10^{-7}T$$
## b.) F.e.m indotta
![[Pasted image 20250712151710.png|center|500]]
Supponiamo che $|\overrightarrow{B}| \approx B_{0}$ in tutti i punti interni della spira, allora otteniamo $$|\overrightarrow{B}(t)|\approx B_{0}(t)=\frac{\mu_{0}i(t)}{2\pi d}$$
Il flusso del campo magnetico attraverso la spira è $$\Phi(\overrightarrow{B}(t))\approx \pi \:r^{2}|\overrightarrow{B}(t)|\approx\pi \:r^{2}B_{0}(t)=\frac{\cancel\pi\:r^{2}\mu_{0}i(t)}{2\cancel\pi d}=\frac{1}{2}\mu_{0} \frac{r^{2}}{d}i(t)$$
La f.e.m indotta nella spira, per la legge di Faraday-Neumann vale $$V_{1}(t)=- \frac{d}{dt}\Phi(\overrightarrow{B}(t))=-\frac{1}{2}\mu_{0} \frac{r^{2}}{d} \frac{d\:i(t)}{d}$$
Abbiamo $i(t)=i_{0}\cos(\omega t)$ da cui $$\frac{d\:i(t)}{d}=i_{0}[-\sin(\omega t)]\cdot \omega=-\omega i_{0}\sin(\omega t)$$ Sostituendolo sopra otteniamo $$V_{1}(t)=-\frac{1}{2}\mu_{0} \frac{r^{2}}{d} \cdot[-\omega i_{0}\sin(\omega t)]=\frac{1}{2}\mu_{0} \omega i_{0}\frac{r^{2}}{d} \sin(\omega t)$$
L'ampiezza delle oscillazioni di $V_{i}(t)$ è $$\begin{align}V=\frac{1}{2}\mu_{0} \omega i_{0}\frac{r^{2}}{d} &=\frac{1}{2}(4\pi\cdot10^{-7}\frac{T\cdot m}{A})\cdot(300\pi \frac{rad}{s})\cdot(1A)\cdot\frac{(0.02m)^{2}}{(0.5m)}\\\\
&\approx4.73\cdot10^{-7}V\approx473nV\end{align}$$
## c.) Corrente indotta e potenza dissipata
Per la legge di Ohm la corrente indotta è $$i_{1}(t)=\frac{v_{1}(t)}{R}=\frac{\mu_{0}\omega i_{0}r^{2}}{2dR}\sin(\omega t)$$
e l'ampiezza delle oscillazioni di $i_{1}(t)$ è $$I=\frac{V}{R}=\frac{4.73\cdot 10^{-7}V}{1 \Omega}=473nA$$
La potenza dissipata nella piccola spira è $$P_{1}(t)=\frac{(v_{1}(t))^{2}}{R}=\frac{1}{R}\bigg(\frac{\mu_{0}\omega i_{0}r^{2}}{2d}\bigg)^{2}\sin^{2}(\omega t)$$
ricordando la formula trigonometrica $$\sin^{2}(\alpha)=\frac{1-\cos(2\alpha)}{2}$$ allora otteniamo $$P_{1}(t)=\frac{1}{2R}\bigg(\frac{\mu_{0}\omega i_{0}r^{2}}{2d}\bigg)^{2}[1-\cos(2\omega t) ]$$
La potenza dissipata oscilla con frequenza $2f$ attorno al valore medio $$\overline{P_{1}} =P_{1}(t)=\frac{1}{2R}\bigg(\frac{\mu_{0}\omega i_{0}r^{2}}{2d}\bigg)^{2}=\frac{V^{2}}{2R}\approx1.122\cdot 10^{-13}W =112.2 fW$$